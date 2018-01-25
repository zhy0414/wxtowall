package com.zsoft.wx.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zsoft.wx.model.TowallUser;

public interface WxtowallService {
	//获得新消息
	public String getNewMessage(HttpServletRequest request);
	//查找可接收信息用户
    public List<TowallUser> selectActiveUser();
    //得到所有待发信息
    //public List<TowallMessage> selectSendMsg();
    
    //查找所有上墙用户
    public List<TowallUser> selectAllUser();
    public String selectAllUserByJson();
    public String selectAllUserToFront();
}
