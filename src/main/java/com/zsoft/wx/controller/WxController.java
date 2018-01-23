package com.zsoft.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsoft.wx.service.WxService;
import com.zsoft.wx.util.SignUtil;

/**
 * 微信 Controller
 * @author zhangyong
 *
 */
@Controller
@RequestMapping("wx")
public class WxController {
	
	@Value("#{configProperties['appID']}")
	private String appID;
	
	@Autowired
	private WxService wxService;
	
	@RequestMapping(value="core", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String core(HttpServletRequest request, 
			@RequestParam(value="signature", required=false) String signature, 
			@RequestParam(value="timestamp", required=false) String timestamp, 
			@RequestParam(value="nonce", required=false) String nonce,
			@RequestParam(value="echostr", required=false) String echostr){
		String returnStr = "";
		
		String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)){
			if(SignUtil.checkSignature(signature, timestamp, nonce)){
				returnStr = echostr;
			}
		} else {
			returnStr = wxService.coreService(request);
		}
		
		return returnStr;
	}
}
