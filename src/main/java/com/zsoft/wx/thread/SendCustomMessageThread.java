package com.zsoft.wx.thread;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.zsoft.wx.dao.TowallUserMapper;
import com.zsoft.wx.model.TowallUser;
import com.zsoft.wx.util.CommonUtil;


public class SendCustomMessageThread implements Runnable{
	private static Logger log = LoggerFactory.getLogger(SendCustomThread.class);
	private List<String> jsonMsgs;
	private String requestUrl;
	private TowallUser user;
	private TowallUserMapper towallUserMapper;
	
	public SendCustomMessageThread(List<String> jsonMsgs, String requestUrl, TowallUser user, TowallUserMapper towallUserMapper) {
		this.jsonMsgs = jsonMsgs;
		this.requestUrl = requestUrl;
		this.user = user;
		this.towallUserMapper = towallUserMapper;
	}
	
	public void run() {
		for(String jsonMsg:jsonMsgs){
			JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
			/*
			System.out.println("******************发送客服消息*********************");
			System.out.println("[requestUrl]: "+requestUrl);
			System.out.println("[jsonMsg]: "+jsonMsg);
			System.out.println("[返回]: "+jsonObject);
			System.out.println("***********************************************");
			*/
			if(null != jsonObject){
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				if(0 == errorCode){
					towallUserMapper.updateMsgNum(user.getId());
					
					log.info("客服消息发送到【 username:{} 】成功 errcode:{} errmsg:{} 【jsonMsg:{}】", user.getUsername(), errorCode, errorMsg, jsonMsg);
				} else {
					log.error("客服消息发送到【 username:{} 】失败 errcode:{} errmsg:{} 【jsonMsg:{}】", user.getUsername(), errorCode, errorMsg, jsonMsg);
				}
			}
		}
	}
	
}
