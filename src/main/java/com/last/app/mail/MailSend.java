package com.last.app.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSend {
	FileReader reader;
	BufferedReader buffr;
	StringBuilder sb = new StringBuilder();
	
	public MailSend() {
		try {
			reader = new FileReader(new File("D:/web_app/Javaee_workspace/FinalProject/src/main/java/com/last/app/mail/form.html"));
			buffr = new BufferedReader(reader);

			String data=null;
			while(true){
				data = buffr.readLine();
				if(data==null)break;
				sb.append(data);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffr!=null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void send() {
		//SMTP 주소, 포트, 각종 설정 세팅... 
		Properties prop=System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true"); //tls, ssl 인증기능 사용여부
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");
		
		//권한 객체 인스턴스 생성 
		Authenticator auth=new AuthImpl();
		
		//보낼 메세지 구성...(세션객체가 필요함)
		Session session = Session.getDefaultInstance(prop, auth);
		MimeMessage message = new MimeMessage(session);

		//보낸 시간 설정 
		try {
			message.setSentDate(new Date());
			
			//보낸사람 주소, 받을 사람, 제목, 내용.. 
			message.setFrom(new InternetAddress("zino1187@gmail.com", "Batman"));
			InternetAddress to = new InternetAddress("zino1187@naver.com");
			message.setRecipient(Message.RecipientType.TO , to);
			
			message.setSubject("메일 테스트입니다", "UTF-8");
			
			//message.setText(sb.toString(), "UTF-8");
			message.setContent(sb.toString(), "text/html;charset=utf-8");
			
			//전송 시작!!
			
			Transport.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MailSend().send();
	}
}




