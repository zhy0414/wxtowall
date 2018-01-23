package com.zsoft.wx.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsoft.wx.dao.TowallMessageMapper;
import com.zsoft.wx.dao.TowallUserMapper;
import com.zsoft.wx.dao.WxUserInfoMapper;
import com.zsoft.wx.domain.outbound.TextMessage;
import com.zsoft.wx.model.TowallMessage;
import com.zsoft.wx.model.TowallUser;
import com.zsoft.wx.model.WxUserInfo;
import com.zsoft.wx.service.WxService;
import com.zsoft.wx.thread.SendCustomThread;
import com.zsoft.wx.thread.UpdateUserInfoThread;
import com.zsoft.wx.util.FaceIconUtil;
import com.zsoft.wx.util.GetUserBaseInfoUtil;
import com.zsoft.wx.util.MessageUtil;
import com.zsoft.wx.util.ProfileUtil;
import com.zsoft.wx.util.RedisUtil;
import com.zsoft.wx.util.ThreadPoolUtil;

import redis.clients.jedis.Jedis;

/**
 * 微信服务实现类
 * @author zhangyong
 *
 */
@Service("wxService")
public class WxServiceImpl implements WxService{
	private Logger log = LoggerFactory.getLogger("WxServiceImpl.java");
	
	private static final String ERR_FORMAT = "输入格式错误或您没有参与此次年会，正确格式：年会注册：真实姓名：手机号码 ，其中冒号为中文格式！";
	
	@Autowired
	private TowallUserMapper towallUserMapper;
	
	@Autowired
	private TowallMessageMapper towallMessageMapper;
	
	@Autowired
	private WxUserInfoMapper wxUserInfoMapper;

	@Override
	public String coreService(HttpServletRequest request) {
		String respMessage = null;  
        try {  
            // 默认返回的文本消息内容  
            //String respContent = "请求处理异常，请稍候尝试！";
        	String respContent = "";  
  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");
            // 消息 ID
            String msgId = requestMap.get("MsgId");
  
            // 回复文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
            
            
            TowallUser user = towallUserMapper.selectByOpenid(fromUserName);
  
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            	
            	System.out.println("收到消息【openid】："+fromUserName+"，【类型】；"+msgType+"，【内容】："+requestMap.get("Content"));
            	
                //respContent = "您发送的是文本消息！"+requestMap.get("Content");
            	
            	//文本消息内容
            	String msgContent = requestMap.get("Content").trim();
                
                
                if(null == user){
                	
                    String[] msgList = msgContent.split("：");
                    
                    //如果收到的消息是以  "年会注册:" 开头
                    if(msgContent.startsWith("年会注册：")){
                    	if(msgList.length != 3){
                    		respContent = ERR_FORMAT;
                    	} else if (msgList[1].equals("") || msgList[2].equals("")){
                    		respContent = ERR_FORMAT;
                    	} else {
                    		user = towallUserMapper.selectByUsernameAndMobile(msgList[1].trim(), msgList[2].trim());
                    		if(null == user){
                    			respContent = ERR_FORMAT;
                    		} else {
                    			//获取用户基本信息
                    			GetUserBaseInfoUtil getUserBaseInfoUtil = new GetUserBaseInfoUtil(fromUserName);
                    			WxUserInfo wxUserInfo = getUserBaseInfoUtil.getInfo();
                    			
                    			user.setHeadimgurl(wxUserInfo.getHeadimgurl());
                    			user.setStatus(1);
                    			user.setOpenid(fromUserName);
                    			user.setLastMsgTime(wxUserInfo.getCtime().getTime()/1000);
                    			user.setRegistTime(wxUserInfo.getCtime());
                    			
                    			//查找表中是否已经有此人基本信息
                    			WxUserInfo oldUserInfo = wxUserInfoMapper.selectByOpenid(fromUserName);
                    			if(null == oldUserInfo){
                    				wxUserInfoMapper.insertSelective(wxUserInfo);
                    			} else {
                    				wxUserInfo.setId(oldUserInfo.getId());
                    				wxUserInfoMapper.updateByPrimaryKey(wxUserInfo);
                    			}
                    			
                				towallUserMapper.updateByPrimaryKeySelective(user);
                				respContent = "【"+user.getUsername()+"】已成功加入！期间请最少每天回复一条消息，否则会收不到其他人的信息！同时您发布的每条消息都将传递给年会的所有参与者！";
                				//获取用户基本信息
                				getUserBaseInfo(user);
                    		
                				TowallMessage message = new TowallMessage();
                            	message.setUsername(user.getUsername());
                            	message.setOpenid(user.getOpenid());
                            	message.setMsgType(0);
                            	message.setMsgId(msgId);
                            	message.setMsgText("加入！共【"+towallUserMapper.countall()+"】人！");
                            	message.setStatus(0);
                            	message.setCtime(user.getRegistTime().getTime()/1000);
                            	
                            	List<TowallUser> activeUsers = towallUserMapper.selectActiveUser();
                            	
                            	SendCustomThread sct = new SendCustomThread(message, activeUsers, towallUserMapper);
                            	ThreadPoolUtil.getInstance().submit(sct);
                            	//Thread sendThread = new Thread(sct);
                            	//sendThread.start();
                    		}
                    	}
                    }
                } else {
                	Date curDate = new Date();
                	FaceIconUtil fiu = FaceIconUtil.getInstance();
                	
                	TowallMessage message = new TowallMessage();
                	message.setUsername(user.getUsername());
                	message.setOpenid(user.getOpenid());
                	message.setMsgType(0);
                	message.setMsgId(msgId);
                	message.setMsgText(msgContent);
                	message.setMsgTextPc(fiu.replace(msgContent, request.getContextPath()+"/views/images/faceicon"));
                	message.setStatus(0);
                	message.setCtime(curDate.getTime()/1000);
                	
                	//记录消息
                	towallMessageMapper.insert(message);
                	
                	List<TowallUser> activeUsers = towallUserMapper.selectActiveUser();
                	
                	SendCustomThread sct = new SendCustomThread(message, activeUsers, towallUserMapper);
                	ThreadPoolUtil.getInstance().submit(sct);
                	//Thread sendThread = new Thread(sct);
                	//sendThread.start();
                	
                	//更新此用户最后登录时间
                	//towallUserMapper.updateLastmsgtimeAndMesnum(user.getId());
                	
                }
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                //respContent = "您发送的是图片消息！";  
            	
            	//图片消息内容
            	String picUrl = requestMap.get("PicUrl").trim();
            	String mediaId = requestMap.get("MediaId").trim();
            	
                
                if(null != user){
                	Date curDate = new Date();
                	
                	TowallMessage message = new TowallMessage();
                	message.setUsername(user.getUsername());
                	message.setOpenid(user.getOpenid());
                	message.setMsgType(1);
                	message.setMsgId(msgId);
                	message.setMsgMediaId(mediaId);
                	message.setMsgPath(picUrl.replace("http://mmbiz.qpic.cn/mmbiz/", "https://mmbiz.qlogo.cn/mmbiz/"));
                	message.setStatus(0);
                	message.setCtime(curDate.getTime()/1000);
                	
                	//记录消息
                	towallMessageMapper.insert(message);
                	
                	List<TowallUser> activeUsers = towallUserMapper.selectActiveUser();
                	
                	SendCustomThread sct = new SendCustomThread(message, activeUsers, towallUserMapper);
                	ThreadPoolUtil.getInstance().submit(sct);
                	//Thread sendThread = new Thread(sct);
                	//sendThread.start();
                	
                	//更新此用户最后登录时间
                	//towallUserMapper.updateLastmsgtimeAndMesnum(user.getId());
                	
                }
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                //respContent = "您发送的是地理位置消息！";  
            	respContent = "";
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                //respContent = "您发送的是链接消息！";  
            	respContent = "";
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                //respContent = "您发送的是音频消息！";
            	
            	//音频消息内容
            	String mediaId = requestMap.get("MediaId").trim();
            	
                
                if(null != user){
                	Date curDate = new Date();
                	
                	TowallMessage message = new TowallMessage();
                	message.setUsername(user.getUsername());
                	message.setOpenid(user.getOpenid());
                	message.setMsgType(2);
                	message.setMsgId(msgId);
                	message.setMsgMediaId(mediaId);
                	message.setStatus(0);
                	message.setCtime(curDate.getTime()/1000);
                	
                	//记录消息
                	towallMessageMapper.insert(message);
                	
                	List<TowallUser> activeUsers = towallUserMapper.selectActiveUser();
                	
                	SendCustomThread sct = new SendCustomThread(message, activeUsers, towallUserMapper);
                	ThreadPoolUtil.getInstance().submit(sct);
                	//Thread sendThread = new Thread(sct);
                	//sendThread.start();
                	
                	//更新此用户最后登录时间
                	//towallUserMapper.updateLastmsgtimeAndMesnum(user.getId());
                	
                }
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    //respContent = WeixinStringUtil.findMainMessage();  
                	respContent = "";
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // TODO 自定义菜单权没有开放，暂不处理该类消息  
                }
            }  
            
            if(!respContent.equals("")){
            	textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            
          
            if(null != user){
            	//更新此用户最后登录时间
            	log.info("【刷新发言用户 "+user.getUsername()+" 的最后刷新时间个接收信息条数】");
            	towallUserMapper.updateLastmsgtimeAndMesnum(user.getId());
            }
            
            //获取用户基本信息，此处不用再获取了，废弃
            /*if(null != user){
            	Jedis jedis = RedisUtil.getJedis();
            	String expired = jedis.get(ProfileUtil.getInstance().read("redis.properties", "USER_INFO_EXPIRED"));
            	if(null == expired){
            		ThreadPoolUtil.getInstance().submit(new UpdateUserInfoThread(towallUserMapper, wxUserInfoMapper, user));
            		jedis.setex("USER_INFO_EXPIRED", 3600, user.getOpenid());
            	}
            	RedisUtil.returnResource(jedis);
            }*/
            
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;
	}
	
	
	/**
	 * 获取用户基本信息
	 * @param user
	 */
	public void getUserBaseInfo(TowallUser user){
		Jedis jedis = RedisUtil.getJedis();
    	String expired = jedis.get(ProfileUtil.getInstance().read("redis.properties", "USER_INFO_EXPIRED"));
    	if(null == expired){
    		ThreadPoolUtil.getInstance().submit(new UpdateUserInfoThread(towallUserMapper, wxUserInfoMapper, user));
    		jedis.setex("USER_INFO_EXPIRED", 3600, user.getOpenid());
    	}
    	RedisUtil.returnResource(jedis);
	}
	
	
	
}
