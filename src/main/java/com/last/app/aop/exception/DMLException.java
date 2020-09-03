package com.last.app.aop.exception;

//CRUD 예외 (사용자들이 알아야할 결과들을 처리하기 위한 예외)
public class DMLException extends BusinessException{
	public DMLException(String msg) {
		super(msg);
	}
	public DMLException(Throwable e) {
		super(e);
	}
	public DMLException(String msg, Throwable e) {
		super(msg,e);
	}
	
}
