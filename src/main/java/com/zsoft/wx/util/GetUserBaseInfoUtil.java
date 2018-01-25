package com.zsoft.wx.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zsoft.wx.model.WxUserInfo;


/**
 * 获取用户基础信息工具
 * @author zhangyong
 */
public class GetUserBaseInfoUtil {
	public final static String USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public final static String USERINFO_BATCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	private static Logger log = LoggerFactory.getLogger("GetUserBaseInfoUtil.class");
	
	private String openid;
	
	public GetUserBaseInfoUtil(){}
	
	public GetUserBaseInfoUtil(String openid){
		this.openid = openid;
	}
	
	/**
	 * 批量获取
	 * @param infos
	 * @return
	 */
	public List<WxUserInfo> getInfos(List<WxUserInfo> infos){
		List<WxUserInfo> returnInfos = new ArrayList<WxUserInfo>();
		
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<infos.size(); i++){
			if(i%100 == 0){
				sb = new StringBuffer();
				sb.append("{\"user_list\":[");
			}
			sb.append("{\"openid\":\""+infos.get(i).getOpenid()+"\", \"lang\":\"zh-CN\"}");
			
			if((i%100 == 0 && i != 0) || i == infos.size()-1){
				sb.append("]}");
				returnInfos.addAll(getInfos(sb.toString()));
			} else {
				sb.append(",");
			}
		}
		
		
		return returnInfos;
	}
	
	
	private List<WxUserInfo> getInfos(String postStr){
		List<WxUserInfo> infos = new ArrayList<WxUserInfo>();
		
		WxUserInfo wxUserInfo = null;
		
		String accessToken = CommonUtil.getToken();
		if(null != accessToken){
			String requestUrl = USERINFO_BATCH_URL.replace("ACCESS_TOKEN", accessToken);
			
			JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", postStr);
			
			//System.out.println("【jsonObject】:"+jsonObject);
			
			if(null != jsonObject){
				
				try {
					JSONArray jsonArray = jsonObject.getJSONArray("user_info_list");
					
					for(int i=0; i<jsonArray.size(); i++){
						JSONObject json = jsonArray.getJSONObject(i);
						
						wxUserInfo = new WxUserInfo();
						wxUserInfo.setSubscribe(json.getIntValue("subscribe"));
						wxUserInfo.setOpenid(json.getString("openid"));
						wxUserInfo.setNickname(json.getString("nickname"));
						wxUserInfo.setSex(json.getIntValue("sex"));
						wxUserInfo.setCity(json.getString("city"));
						wxUserInfo.setCountry(json.getString("country"));
						wxUserInfo.setProvince(json.getString("province"));
						wxUserInfo.setLanguage(json.getString("language"));
						wxUserInfo.setHeadimgurl(json.getString("headimgurl"));
						wxUserInfo.setSubscribeTime(json.getLong("subscribe_time"));
						//wxUserInfo.setUnionid(json.getString("unionid"));
						wxUserInfo.setRemark(json.getString("remark"));
						wxUserInfo.setGroupid(json.getString("groupid"));
						wxUserInfo.setCtime(new Date());
						
						infos.add(wxUserInfo);
						
						log.info("【获得用户基本信息成功】 jsonObject:{}", json);
					}
					
					
				} catch (JSONException e) {
					e.printStackTrace();
					
					int errorCode = jsonObject.getIntValue("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					log.error("【获得用户基本信息失败】 errcode:{} errmsg:{}", errorCode, errorMsg);
				}
				
			}
		}
		
		return infos;
	}
	
	
	/**
	 * 获取消息
	 * @return 失败时返回 null
	 */
	public WxUserInfo getInfo(){
		WxUserInfo wxUserInfo = null;
		
		String accessToken = CommonUtil.getToken();
		if(null != accessToken){
			String requestUrl = USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
			
			JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
			
			//System.out.println("【jsonObject】:"+jsonObject);
			
			if(null != jsonObject){
				try {
					wxUserInfo = new WxUserInfo();
					wxUserInfo.setSubscribe(jsonObject.getIntValue("subscribe"));
					wxUserInfo.setOpenid(jsonObject.getString("openid"));
					wxUserInfo.setNickname(jsonObject.getString("nickname"));
					wxUserInfo.setSex(jsonObject.getIntValue("sex"));
					wxUserInfo.setCity(jsonObject.getString("city"));
					wxUserInfo.setCountry(jsonObject.getString("country"));
					wxUserInfo.setProvince(jsonObject.getString("province"));
					wxUserInfo.setLanguage(jsonObject.getString("language"));
					wxUserInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
					wxUserInfo.setSubscribeTime(jsonObject.getLong("subscribe_time"));
					//wxUserInfo.setUnionid(jsonObject.getString("unionid"));
					wxUserInfo.setRemark(jsonObject.getString("remark"));
					wxUserInfo.setGroupid(jsonObject.getString("groupid"));
					wxUserInfo.setCtime(new Date());
					
					log.info("【获得用户基本信息成功】 jsonObject:{}", jsonObject);
				} catch (JSONException e) {
					e.printStackTrace();
					
					int errorCode = jsonObject.getIntValue("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					
					log.error("【获得用户基本信息失败】 errcode:{} errmsg:{}", errorCode, errorMsg);
				}
			}
		}
		
		return wxUserInfo;
	}

}
