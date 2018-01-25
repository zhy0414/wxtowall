package com.zsoft.wx.domain.outbound;

import com.zsoft.wx.model.TowallMessage;

/**
 * 客服接口文本类
 * @author zhangyong
 *
 */
public class CustomText {
	private String touser;
	private String msgtype;
	private String content;
	
	public CustomText(){}
	
	public CustomText(TowallMessage message, String touser){
		this.touser = touser;
		this.msgtype = "text";
		this.content = message.getUsername()+"："+message.getMsgText();
	}
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toJson() {
		return "{\"touser\":\"" + touser + "\", \"msgtype\":\"" + msgtype + "\", \"text\":{\"content\":\"" + content.replace("\"", "\\\"") + "\"}}";
	}
	
	
}
