package com.ssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.User;
import com.ssm.service.IUserService;

@Controller
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("/showUser")
	public ModelAndView toIndex(HttpServletRequest request, Model model){
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		ModelAndView modelAndView = new ModelAndView("test");
		modelAndView.addObject("user", user);
		
		User user1 = new User();
		user1.setId(null);
		user1.setAge(26);
		user1.setUserName("test_name1");
		user1.setPassword("password1");
		userService.doInsert(user1);
		
		user1.setAge(28);
		userService.doUpdate(user1);
		
		logger.info(user);
		logger.info(user.getUserName());
		return modelAndView;
	}
}
