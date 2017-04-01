package com.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义系统异常，在controller中手动抛出此异常可以跳转到异常界面error.jsp
 * @author jliu10
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	private static Logger logger = Logger.getLogger(CustomExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		 //解析出异常类型  
        //如果该异常类型是系统自定义的异常，直接取出异常信息，在错误页面展示  
        //上边的代码变为  
        CustomException customException = null;  
        if(ex instanceof CustomException){  
            customException = (CustomException) ex;  
        }else{//如果该异常类型不是系统自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）  
            customException = new CustomException("未知错误");  
        }  
        logger.error(ex, ex);
        //错误信息  
        String message = customException.getMessage();  
        //定义modelAndView  
        ModelAndView modelAndView = new ModelAndView();  
        //将错误信息传到页面  
        modelAndView.addObject("message",message);  
        //指向错误页面  
        modelAndView.setViewName("error/error");  
        return modelAndView;  
	}

}
