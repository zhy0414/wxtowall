package com.zsoft.wx.model;

public class TowallMessage {
    private Integer id;

    private String username;

    private String openid;

    private Integer msgType;

    private String msgText;

    private String msgTextPc;

    private String msgId;

    private String msgMediaId;

    private String msgTitle;

    private String msgDescription;

    private String msgPath;

    private Integer status;

    private Long ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText == null ? null : msgText.trim();
    }

    public String getMsgTextPc() {
        return msgTextPc;
    }

    public void setMsgTextPc(String msgTextPc) {
        this.msgTextPc = msgTextPc == null ? null : msgTextPc.trim();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getMsgMediaId() {
        return msgMediaId;
    }

    public void setMsgMediaId(String msgMediaId) {
        this.msgMediaId = msgMediaId == null ? null : msgMediaId.trim();
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getMsgDescription() {
        return msgDescription;
    }

    public void setMsgDescription(String msgDescription) {
        this.msgDescription = msgDescription == null ? null : msgDescription.trim();
    }

    public String getMsgPath() {
        return msgPath;
    }

    public void setMsgPath(String msgPath) {
        this.msgPath = msgPath == null ? null : msgPath.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }
}