package com.zsoft.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zsoft.wx.service.WxtowallService;


@Controller
@RequestMapping("towall")
public class WxtowallController {
	private static Logger logger = LoggerFactory.getLogger(WxtowallController.class);
	@Autowired
	private WxtowallService wxtowallService;
	
	@RequestMapping(value="wxwall")
	public ModelAndView wall(){
		ModelAndView modelAndView = new ModelAndView("/front/towall/wall");
		
		return modelAndView;
	}
	
	
	
	/**
	 * 获取墙上显示的信息
	 * @return
	 */
	@RequestMapping(value="ajaxwallmessage", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String ajaxWallMessage(HttpServletRequest request){
		//String returnStr = wxtowallService.getNewMessage(request);
		
		
		//returnStr = "[{\"ctime\":1452254145,\"id\":19,\"msgId\":\"6237384067246692635\",\"msgText\":\"测9\",\"msgType\":0,\"openid\":\"oKMiujmRazg_SiQpENk9Vw-J6n2k\",\"status\":0,\"username\":\"张勇\"},{\"ctime\":1452254154,\"id\":20,\"msgId\":\"6237384101606431006\",\"msgText\":\"测10\",\"msgType\":0,\"openid\":\"oKMiujmRazg_SiQpENk9Vw-J6n2k\",\"status\":0,\"username\":\"张勇\"},{\"ctime\":1452254159,\"id\":21,\"msgId\":\"6237384123081267489\",\"msgText\":\"测11\",\"msgType\":0,\"openid\":\"oKMiujmRazg_SiQpENk9Vw-J6n2k\",\"status\":0,\"username\":\"张勇\"}]";
		
		String returnStr = wxtowallService.getNewMessage(request);
		
		logger.info("【上墙新消息】："+returnStr);
		
		return returnStr;
	}
	
	/**
	 * 获取用户列表
	 * @return
	 */
	@RequestMapping(value="ajaxwallusers", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String ajaxWallUsers(){
		//String returnStr = wxtowallService.selectAllUserByJson();
		String returnStr = wxtowallService.selectAllUserToFront();
		return returnStr;
	}
	
	/**
	 * 注册页面，已过期
	 */
	/*@RequestMapping(value="regist")
	public ModelAndView regist(){
		ModelAndView modelAndView = new ModelAndView("/towall/regist");
		return modelAndView;
	}*/
}
