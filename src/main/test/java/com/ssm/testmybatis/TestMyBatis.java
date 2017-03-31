package com.ssm.testmybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ssm.aop.TestService;
import com.ssm.pojo.User;
import com.ssm.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class TestMyBatis {

	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	
	@Resource
	private IUserService userService = null;
	
	@Resource
	private TestService testService = null;
	
	@Test
	public void test(){
		User user = userService.getUserById(1);
		logger.info(JSON.toJSONString(user));
		List<String> userName = userService.getUserNameById(1);
		logger.info("*******************"+userName);
		
	        System.out.println("----------------save start------------");  
	        testService.save("我是参数");  
	        System.out.println("----------------save end------------");  
	  
	  
	        System.out.println("----------------update start------------");  
	        testService.update(100);  
	        System.out.println("----------------update end------------");  
	  
	        System.out.println("----------------throwException start------------");  
	        testService.throwException();  
	        System.out.println("----------------throwException end------------");  
	        
	        System.out.println("----------------getPersonName start------------");  
	        String result = testService.getPersonName();  
	        System.out.println(result);  
	        System.out.println("----------------getPersonName end------------");
	
	}
	
}
