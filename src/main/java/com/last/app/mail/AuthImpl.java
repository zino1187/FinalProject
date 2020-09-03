package com.last.app.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AuthImpl extends Authenticator{
	//password는 String이 아니라 이미 정해진 객체가 있다!!
	PasswordAuthentication pass;
	
	public AuthImpl() {
		String userName="zino1187";
		String password="";
		
		pass = new PasswordAuthentication(userName, password);
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return pass;
	}
}








