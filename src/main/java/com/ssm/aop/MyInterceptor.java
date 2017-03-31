package com.ssm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyInterceptor {

	@Pointcut("execution(* com.ssm.aop.*.*(..))")
	private void anyMethod() {
		System.out.println("1234567");
	}

	@Before("anyMethod() && args(name)")
	// 声明前置通知
	public void doBefore(String name) {
		System.out.println("前置通知");
		System.out.println("---" + name + "---");
	}

	@AfterReturning(pointcut = "anyMethod()", returning = "result")
	// 声明后置通知
	public void doAfterReturning(String result) {
		System.out.println("后置通知");
		System.out.println("---" + result + "---");
	}

	@AfterThrowing(pointcut = "anyMethod()", throwing = "e")
	// 声明例外通知
	public void doAfterThrowing(Exception e) {
		System.out.println("例外通知");
		System.out.println(e.getMessage());
	}

	@After("anyMethod()")
	// 声明最终通知
	public void doAfter() {
		System.out.println("最终通知");
	}

	@Around("anyMethod()")
	// 声明环绕通知
	public Object doAround(ProceedingJoinPoint pjp){
		System.out.println("进入方法---环绕通知");
		long start = System.currentTimeMillis();
		// 显示调用，确保被代理的方法被调用
		Object o = null;
		try{
			o = pjp.proceed();
		}catch(Throwable e){
			
		}finally{
			long end = System.currentTimeMillis();
			System.out.println("退出方法---环绕通知 , 方法执行时间：" +(end - start) + "ms.");
		}
		return o;
	}
}
