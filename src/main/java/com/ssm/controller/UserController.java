package com.ssm.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.ssm.aop.controller.annotation.OperationLog;
import com.ssm.exception.CustomException;
import com.ssm.pojo.User;
import com.ssm.service.IUserService;

@Controller
@RequestMapping("/test")
public class UserController extends CommonController  {
	
	private static Logger logger = Logger.getLogger(UserController.class);

	
	@Resource
	private IUserService userService;
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@OperationLog(modalName="用户信息", operationType = OperationLog.OperationType.QUERY, operationLevel = OperationLog.OperationLevel.PROMPT)
	@RequestMapping("/showUser")
	public ModelAndView doShowUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, String lang){	
		logger.info("controller 方法开始执行 : " + request.getRequestURL());
//		setLanguage(request, response, new SessionLocaleResolver()); //通过session来设置语言环境
		setLanguage(request, response, new CookieLocaleResolver()); //通过cookie来设置语言环境
		
		User us = new User();
		us.setAge(33);
		us.setUserName("name");
		us.setPassword("asdfqwer");
		this.userService.doInsert(us);
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		ModelAndView modelAndView = new ModelAndView("test");
		modelAndView.addObject("user", user);
		modelAndView.addObject("language_message", getMessage("language.test.message"));
		modelAndView.addObject("operation_result", OperationLog.OperationResult.SUCCESS);
		logger.info("controller 方法执行完毕 : " + request.getRequestURL());
		return modelAndView;
	}
	
	@RequestMapping("/testException")
	public ModelAndView doException(HttpServletRequest request, Model model) throws Exception{
		String userId = request.getParameter("userId");
		if( null == userId ){
			throw new CustomException("userID异常");
		}
		ModelAndView modelAndView = new ModelAndView("test");
		return modelAndView;
	}
	
	public void setLanguage(HttpServletRequest request, HttpServletResponse response, LocaleContextResolver obj){
		String langType = request.getParameter("language");  
        if("zh".equals(langType)){  
            Locale locale = new Locale("zh", "CN");   
            obj.setLocale(request, response, locale);
        }  
        else if("en".equals(langType)){  
            Locale locale = new Locale("en", "US");   
            obj.setLocale(request, response, locale);
        }else{
        	obj.setLocale (request, response,  
                    LocaleContextHolder.getLocale());  
        }
	}
}
