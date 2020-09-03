package com.last.app.sns;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;


public class NaverOAuth2Operations {
	public static final String AUTHORIZATION_CODE="code";
	private NaverOAuth2Parameters naverOAuth2Parameters;
	private String client_id;
	private String client_secret;
	
	public String buildAuthenticateUrl(String code, NaverOAuth2Parameters naverOAuth2Parameters) {
		naverOAuth2Parameters.getRedirectUri();
		
	    String clientId = this.client_id;//애플리케이션 클라이언트 아이디값";
	    
	    String redirectURI=null;
		try {
			redirectURI = URLEncoder.encode(naverOAuth2Parameters.getRedirectUri(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    StringBuilder sb = new StringBuilder();
	    sb.append("https://nid.naver.com/oauth2.0/authorize?response_type="+AUTHORIZATION_CODE);
	    sb.append("&client_id=" + clientId);
	    sb.append("&redirect_uri=" + redirectURI);
	    sb.append("&state=" + state);
	    
		return sb.toString();
	}
	
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
}
