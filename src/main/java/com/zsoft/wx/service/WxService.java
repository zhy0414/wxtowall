package com.zsoft.wx.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zsoft.wx.model.TowallMessage;
import com.zsoft.wx.model.TowallUser;

/**
 * 微信服务类
 * @author zhangyong
 *
 */
public interface WxService {
	public String coreService(HttpServletRequest request);
	
}
