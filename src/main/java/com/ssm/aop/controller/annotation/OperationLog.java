package com.ssm.aop.controller.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义注解，结合aop进行面向切面的操作日志管理
 * @author jliu10
 *
 */
@Retention(RetentionPolicy.RUNTIME) //注解会在class中存在，运行时可通过反射获取
@Target(ElementType.METHOD)
@Documented//文档生成时，该注解将被包含在javadoc中，可去掉
public @interface OperationLog {

	public static enum OperationType{
		QUERY,  //查询
		ADD,   //增加
		EDIT,  //编辑
		DELETE //删除
	}
	
	public static enum OperationLevel{
		GENERAL,  //一般
		PROMPT,  //提示
		WARNING,  //警告
		DANGEROUS, //危险
		SERIOUS, //严重
		DEADLY //致命
	}
	
	public static enum OperationResult{
		SUCCESS, //成功
		FAILED,	//失败
		EXCEPTION	//异常
	}
	
	/**
	 * 获取模块名称
	 * @return
	 */
	String modalName();
	
	/**
	 * 获取操作类型
	 * @return
	 */
	OperationType operationType();
	
	/**
	 * 获取操作的级别
	 * @return
	 */
	OperationLevel operationLevel();
}
