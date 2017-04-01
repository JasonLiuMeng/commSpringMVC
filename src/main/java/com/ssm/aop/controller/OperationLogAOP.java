package com.ssm.aop.controller;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.aop.controller.annotation.OperationLog;

@Component
@Aspect
public class OperationLogAOP {

	private static Logger logger = Logger.getLogger(OperationLogAOP.class);
	
	/**
	 * 
		声明事务切点  execution( * com.ssm.controller..*.*(..))
		 1、execution(): 表达式主体。
		 2、第一个*号：表示返回类型，*号表示所有的类型。
		 3、包名：表示需要拦截的包名，后面的两个句点(..)表示当前包和当前包的所有子包，com.ssm.service包、子孙包下所有类的方法。
		 4、第二个*号：表示类名，*号表示所有的类。
		 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点(..)表示任何参数
	@Pointcut("execution(* com.ssm.controller.*.*(..))")
	*/
	
	/*
	 * 将切面与自定义注解进行绑定
	 */
	@Pointcut("@annotation(com.ssm.aop.controller.annotation.OperationLog)")
	private void anyMethod() {
	}

	@Before("anyMethod() && args(language)")
	// 声明前置通知
	public void doBefore(String language) {
		logger.info("我是AOP的前置通知，当方法的参数中有language时我会执行... language is : " + language);
	}

	@AfterReturning(pointcut = "anyMethod()", returning = "result")
	// 声明后置通知
	public void doAfterReturning(String result) {
		logger.info("我是AOP的后置通知，当方法有返回结果时我会执行... result is : " + result);
	}

	@AfterThrowing(pointcut = "anyMethod()", throwing = "e")
	// 声明例外通知
	public void doAfterThrowing(Exception e) {
		logger.info("我是AOP的异常通知，当方法抛出异常时，我会执行... Exception : " + e.getMessage());
	}

	@After("anyMethod()")
	// 声明最终通知
	public void doAfter() {
		logger.info("我是AOP的最终通知，当方法完全执行完毕之后我会执行...");
	}

	@Around("anyMethod()")
	// 声明环绕通知
	public Object doAround(ProceedingJoinPoint pjp){
		logger.info("我是AOP的环绕方法，我是调用切点方法的入口...");
		// 显示调用，确保被代理的方法被调用
		Object o = null;
		try{
			o = pjp.proceed(); //调用方法
		}catch(Throwable e){
			
		}finally{
			parseOperation(pjp, o);
			logger.info("我是AOP的环绕方法，我是调用切点方法执行完毕之后的方法...");
		}
		return o;
	}
	
	private void parseOperation(JoinPoint joinPoint, Object o){
		OperationLog operationLog = getAnnotationFromMethod(joinPoint);
		if( null == operationLog ||  null == o ){
			return;
		}
		Object result = null;
		if( o instanceof ModelAndView ){
			o = (ModelAndView)o;
			result =((ModelAndView) o).getModel().get("operation_result");
		}else if( o instanceof Model  ){
			result = ((Model) o).asMap().get("operation_result");
		}else if( o instanceof String ){
			result = o;
		}
		if( result instanceof OperationLog.OperationResult ){
			result = ((OperationLog.OperationResult)result).name();
		}
		//对于操作记录可以插入数据库，此处为了简单直接打印
		logger.info("操作模块--" + operationLog.modalName() 
				+ "; 操作类型--" + operationLog.operationType() 
				+ "; 操作级别--" + operationLog.operationLevel()
				+ "; 操作结果--" + result);
	}
	
	public static OperationLog getAnnotationFromMethod(JoinPoint joinPoint){
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		if( null != method ){
			return method.getAnnotation(OperationLog.class);
		}
		return null;
	}
}
