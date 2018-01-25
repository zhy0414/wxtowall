package com.zsoft.wx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 通用拦截器
 * @author zhangyong
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	private final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	
	/**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */ 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/*if("GET".equalsIgnoreCase(request.getMethod())) {
			RequestUtil.saveRequest();
		}*/
		
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		
		//logger.info("requestUri:" + requestUri);
		//logger.info("contextPath:" + contextPath);
		//logger.info("url:" + url);
		
		logger.info("requestUri:" + requestUri);
		logger.info("contextPath:" + contextPath);
		logger.info("url:" + url);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("uid") == null){
			System.err.println("requestUri:" + requestUri);
			System.err.println("contextPath:" + contextPath);
			System.err.println("url:" + url);
			request.getRequestDispatcher("/login/login").forward(request, response);
			return false;
		} else if(session.getAttribute("uid").equals("/login/login")) {
			request.getRequestDispatcher("/index/index").forward(request, response);	
			return true;
		} else {
			
			
			return true;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		if(modelAndView!=null)
		modelAndView.addObject("left_menu", session.getAttribute("left_menu"));
		
		
	}
	
	
}
