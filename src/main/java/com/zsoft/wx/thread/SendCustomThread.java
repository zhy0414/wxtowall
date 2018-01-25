package com.zsoft.wx.thread;

import java.util.ArrayList;
import java.util.List;

import com.zsoft.wx.dao.TowallUserMapper;
import com.zsoft.wx.domain.outbound.CustomImage;
import com.zsoft.wx.domain.outbound.CustomText;
import com.zsoft.wx.domain.outbound.CustomVoice;
import com.zsoft.wx.model.TowallMessage;
import com.zsoft.wx.model.TowallUser;
import com.zsoft.wx.util.CommonUtil;
import com.zsoft.wx.util.RedisUtil;
import com.zsoft.wx.util.ThreadPoolUtil;

import redis.clients.jedis.Jedis;

/**
 * 发送客服消息线程
 * @author zhangyong
 *
 */
public class SendCustomThread  implements Runnable{
	public final static String CUSTOM_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	//private static Logger log = LoggerFactory.getLogger(SendCustomThread.class);
	
	//private List<String> openids;
	private TowallMessage message;
	private List<TowallUser> activeUsers;
	private TowallUserMapper towallUserMapper;
	
	public SendCustomThread(TowallMessage message, List<TowallUser> activeUsers, TowallUserMapper towallUserMapper) {
		Jedis jedis = RedisUtil.getJedis();
		
		//openids = jedis.lrange("users_action", 0, -1);
		this.message = message;
		this.activeUsers = activeUsers;
		this.towallUserMapper = towallUserMapper;
		
		RedisUtil.returnResource(jedis);
	}
	

	public void run() {
		String messageUser = message.getOpenid();
		int messageType = message.getMsgType();
		List<String> jsonMsgs = null;
		
		String accessToken = CommonUtil.getToken();
		if(null != accessToken){
			String requestUrl = CUSTOM_URL.replace("ACCESS_TOKEN", accessToken);
			
			//towallUserMapper.updateLastmsgtimeAndMesnum(user.getId());
			
			System.out.println("*************activeUsers is:**************");
			for(TowallUser user : activeUsers){
				System.out.println("发送给：【"+user.getUsername()+"】");
			}
			System.out.println("**************end***************8");
			//从接收者中将自己去掉
			for(TowallUser user : activeUsers){
				
				
				if(!user.getOpenid().equals(messageUser)){		//发送者
					jsonMsgs = new ArrayList<String>();
					
					if(messageType == 0){				//文字消息
						CustomText ct = new CustomText(message, user.getOpenid());
						jsonMsgs.add(ct.toJson());
					} else if(messageType == 1){		//图片消息
						CustomText ct = new CustomText();
						ct.setTouser(user.getOpenid());
						ct.setMsgtype("text");
						ct.setContent(message.getUsername()+" 上传：");
						jsonMsgs.add(ct.toJson());
						
						CustomImage ci = new CustomImage(message, user.getOpenid());
						jsonMsgs.add(ci.toJson());
					} else if(messageType == 2){
						CustomText ct = new CustomText();
						ct.setTouser(user.getOpenid());
						ct.setMsgtype("text");
						ct.setContent(message.getUsername()+" 说：");
						jsonMsgs.add(ct.toJson());
						
						CustomVoice cv = new CustomVoice(message, user.getOpenid());
						jsonMsgs.add(cv.toJson());
					}
					
					if(user.getMsgNum() >= 18){
						CustomText ct = new CustomText();
						ct.setTouser(user.getOpenid());
						ct.setMsgtype("text");
						ct.setContent("请回复【1】继续接收群消息，否则直到下次和群互动时才能继续接收到群消息！");
						
						jsonMsgs.add(ct.toJson());
					}
					
					
					SendCustomMessageThread scmt = new SendCustomMessageThread(jsonMsgs, requestUrl, user, towallUserMapper);
					ThreadPoolUtil.getInstance().submit(scmt);
					//Thread sendThread = new Thread(scmt);
                	//sendThread.start();
				}
			}
			
			
		}
		
	}
	
	
	/**
	 * 发送客服消息，转移到 SendCustomMessageThread 中
	 * @param sendMessage 要发送的信息的 json 格式
	 * @return true：发送成功，false：发送失败
	 */
	/*private boolean sendCustomMessage(List<String> jsonMsgs, String requestUrl, TowallUser user, TowallUserMapper towallUserMapper){
		boolean result = false;
		
		for(String jsonMsg:jsonMsgs){
			JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
			
			System.out.println("******************发送客服消息*********************");
			System.out.println("[requestUrl]: "+requestUrl);
			System.out.println("[jsonMsg]: "+jsonMsg);
			System.out.println("[返回]: "+jsonObject);
			System.out.println("***********************************************");
			
			if(null != jsonObject){
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				if(0 == errorCode){
					result = true;
					
					towallUserMapper.updateMsgNum(user.getId());
					
					log.info("客服消息发送到【 username:{} 】成功 errcode:{} errmsg:{} 【jsonMsg:{}】", user.getUsername(), errorCode, errorMsg, jsonMsg);
				} else {
					log.error("客服消息发送到【 username:{} 】失败 errcode:{} errmsg:{} 【jsonMsg:{}】", user.getUsername(), errorCode, errorMsg, jsonMsg);
				}
			}
		}
		
		return result;
	}*/

}
