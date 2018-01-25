package com.zsoft.wx.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zsoft.wx.constants.ServiceCodeConstants;


public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected static int CURPAGE = 1;
	protected static int PAGESIZE = 2;

//	public Map<String, Object> getPageResult(BaseService<Object> ibs, Map<String, Object> map) throws Exception {
//		return ibs.queryPageResult(map);
//	}
	
	protected Map<String, Object> getFailRtn(String msg) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.FAIL_RTN);
		rtn.put("msg", msg);
		rtn.put("data", null);
		return rtn;
	}
	
	/**
	 * 获取成功返回
	 */
	protected Map<String, Object> getSuccessRtn(Object data) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.SUCCESS_RTN);
		rtn.put("msg", "");
		rtn.put("data", data);
		return rtn;
	}
	
	/**
	 * 获取成功返回
	 * @param key 返回的key
	 * @param data 返回值中对应 key 的值
	 */
	protected Map<String, Object> getSuccessRtnByKey(String key, Object data) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.SUCCESS_RTN);
		rtn.put("msg", "");
		rtn.put(key, data);
		return rtn;
	}
	
	/**
	 * 获取成功返回
	 * msg 包含data中的 key 名，如：playlist|other	其中表示 data 中有 playlist 为 key 的数据
	 */
	protected Map<String, Object> getSuccessRtn(String msg, Object data) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.SUCCESS_RTN);
		rtn.put("msg", msg);
		rtn.put("data", data);
		return rtn;
	}
	
	
	
	/**
	 * 成功页面
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("success")
	public ModelAndView success(String message, String url) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/success");
		modelAndView.addObject("message", message);
		modelAndView.addObject("url", url);
		return modelAndView;
	}
	
	/**
	 * 失败页面
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fail")
	public ModelAndView fail(String message, String url) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/fail");
		modelAndView.addObject("message", message);
		modelAndView.addObject("url", url);
		return modelAndView;
	}
}
