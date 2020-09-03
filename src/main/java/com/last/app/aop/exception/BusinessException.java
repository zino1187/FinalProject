package com.last.app.aop.exception;

//프로그램 운용시 발생할 수 있는 업무적 예외의 최상위 객체 정의
public class BusinessException extends RuntimeException{
	public BusinessException(String msg) {
		super(msg);
	}
	public BusinessException(Throwable e) {
		super(e);
	}
	public BusinessException(String msg, Throwable e) {
		super(msg,e);
	}
	
}
