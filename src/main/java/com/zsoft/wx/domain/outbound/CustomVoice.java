package com.zsoft.wx.domain.outbound;

import com.zsoft.wx.model.TowallMessage;

public class CustomVoice {
	private String touser;
	private String msgtype;
	private String mediaId;
	
	public CustomVoice(){}
	
	public CustomVoice(TowallMessage message, String touser) {
		this.touser = touser;
		this.msgtype = "voice";
		this.mediaId = message.getMsgMediaId();
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
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String toJson() {
		return "{\"touser\":\"" + touser + "\", \"msgtype\":\"" + msgtype + "\", \"voice\":{\"media_id\":\"" + mediaId + "\"}}";
	}
}
