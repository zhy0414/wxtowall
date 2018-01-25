package com.zsoft.wx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zsoft.wx.dao.TowallMessageMapper;
import com.zsoft.wx.dao.TowallUserMapper;
import com.zsoft.wx.model.TowallMessage;
import com.zsoft.wx.model.TowallUser;
import com.zsoft.wx.service.WxtowallService;


@Service("wxtowallService")
public class WxtowallServiceImpl implements WxtowallService {
	private static Logger log = LoggerFactory.getLogger("WxtowallServiceImpl.class");
	
	@Autowired
	private TowallUserMapper towallUserMapper;
	
	@Autowired
	private TowallMessageMapper towallMessageMapper;
	
	
	
	/**
	 * 获取新消息
	 * @return 返回 json 格式字符串
	 */
	public String getNewMessage(HttpServletRequest request){
		List<TowallMessage> messages = new ArrayList<TowallMessage>();
		String returnStr = "";
		
		messages = towallMessageMapper.selectNewMsg();
		
		if(messages.size() > 0){
			towallMessageMapper.updateNewMsgStatus(messages);
			
			/*System.out.println("【得到"+messages.size()+"条消息】");
			for(int i=0;i<messages.size();i++){
				System.out.println("【"+i+"】"+messages.get(i).getMsgText());
			}*/
			
			/*for(TowallMessage message : messages) {
				if(message.getMsgType() == 0){
					message.setMsgText(FaceIconUtil.getInstance().replace(message.getMsgText(), request.getContextPath()+"/views/images/faceicon"));
				}
			}*/
			
			returnStr = JSON.toJSONString(messages);
		}
		
		/*String lastTimeStr = ProfileUtil.getInstance().read("redis.properties", "LAST_MSG_TIME");
		Long curTime = (new Date()).getTime()/1000;
		
		Jedis jedis = RedisUtil.getJedis();
		
		String lastTime = jedis.get(lastTimeStr);
		
		System.out.println("【lastTime】 "+lastTime+" 【curTime】 "+curTime);
		if(null != lastTime){
			messages = towallMessageMapper.selectNewMsg(Long.parseLong(lastTime), curTime);
			
			System.out.println("【得到"+messages.size()+"条消息】");
			for(int i=0;i<messages.size();i++){
				System.out.println("【"+i+"】"+messages.get(i).getMsgText());
			}
		}
		
		jedis.set(lastTimeStr, curTime+"");
		
		RedisUtil.returnResource(jedis);
		
		if(messages.size() > 0){
			returnStr = JSON.toJSONString(messages);
		}*/
		
		return returnStr;
	}
	
	//查找可接收信息用户
    public List<TowallUser> selectActiveUser(){
    	return towallUserMapper.selectActiveUser();
    }

    /**
     * 得到所有上墙用户
     * @return 返回 Towalluser 数组
     */
	public List<TowallUser> selectAllUser() {
		return towallUserMapper.selectAllUser();
	}
    
    /**
     * 得到所有上墙用户
     * @return 返回JSON格式，无用户时返回 ''
     */
	public String selectAllUserByJson() {
		String returnStr = "";
		List<TowallUser> users = selectAllUser();
		if(null != users && users.size() > 0){
			returnStr = JSON.toJSONString(users);
		}
		return returnStr;
	}
	
	/**
	 * 传给上墙页面前台的用户信息
	 * @return
	 */
	public String selectAllUserToFront(){
		String returnStr = "";
		List<TowallUser> users = selectAllUser();
		
		if(null != users && users.size() > 0){
			StringBuffer sb = new StringBuffer();
			
			for(TowallUser user:users){
				sb.append("#chat-thread li:nth-child(odd)."+user.getOpenid()+":before{background-image: url("+user.getHeadimgurl()+"); background-size:50px 50px; background-repeat:no-repeat; right:-80px;}");
				sb.append("#chat-thread li:nth-child(even)."+user.getOpenid()+":before{background-image: url("+user.getHeadimgurl()+"); background-size:50px 50px; background-repeat:no-repeat; left:-80px;}");
			}
			
			//returnStr = JSON.toJSONString(users);
			
			returnStr = sb.toString();
		}
		return returnStr;

	}

    //得到所有待发信息
	/*@Override
	public List<TowallMessage> selectSendMsg() {
		return towallMessageMapper.selectSendMsg();
	}*/
}
