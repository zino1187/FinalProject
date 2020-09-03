package com.last.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.last.app.controller.main.HomeController;

public class LogAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
		//타겟의 호출을 중간에서 가로막았으므로, 다시 진행시켜야..
		Object result=joinPoint.proceed();// go head
		logger.info("원래 호출하려고 했던 메서드는 ", joinPoint.getSignature());
		
		return result;
	}
}




