package com.ssm.aop.impl;

import org.springframework.stereotype.Service;

import com.ssm.aop.TestService;

@Service(value = "testService")
public class TestServiceImpl implements TestService{

	@Override  
    public String getPersonName() {  
        System.out.println("我是getPersonName()方法。。。");  
        return "返回结果";  
    }  
  
    @Override  
    public void save(String name) {  
        System.out.println("我是save()方法。。。");  
    }  

    @Override  
    public void throwException() {  
        System.out.println("我是throwException()方法。。。");  
        int i = 10 / 0;
    }
    
    @Override  
    public void update(int name) {  
        System.out.println("我是update()方法。。。");  
    }  

}
