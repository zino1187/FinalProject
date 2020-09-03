package com.last.app.aop.exception;

//CRUD 예외 (사용자들이 알아야할 결과들을 처리하기 위한 예외)
public class MemberNotFoundException extends BusinessException{
	public MemberNotFoundException(String msg) {
		super(msg);
	}
	public MemberNotFoundException(Throwable e) {
		super(e);
	}
	public MemberNotFoundException(String msg, Throwable e) {
		super(msg,e);
	}
	
}
