package com.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ssm.aop.controller.OperationLogAOP;

public class MyTestInterceptor extends HandlerInterceptorAdapter{

	private static Logger logger = Logger.getLogger(OperationLogAOP.class);
	
	@Override
	/**
	 * controller方法执行之前调用
	 * 
	 * @return true则继续调用拦截器链，false则停止后续调用
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		logger.info("我是拦截器的前置方法--preHandle");
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * controller处理完成之后，视图渲染之前进行调用
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("我是拦截器在controll调用完毕之后，视图返回之前的方法--postHandle");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * 整个请求完成之后调用，此时视图已经渲染完成，可以用来清理资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("我是拦截器在请求完成之后的方法，此时视图已经渲染完毕--afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
