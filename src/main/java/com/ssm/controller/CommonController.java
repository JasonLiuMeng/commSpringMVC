package com.ssm.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

public class CommonController {
	
	@Resource  
    private MessageSource messageSource;  
	
	@Autowired  
	private  HttpServletRequest request;
	
	public CommonController() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage(String key){
		Locale local = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
		return messageSource.getMessage(key, null, local);
	}
	
	public String getMessage(String key, Object[] objs){
		Locale local = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
		return messageSource.getMessage(key, objs, local);
	}
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	
}
