package com.last.app.sns;

import lombok.Data;

@Data
public class NaverConnectionFactory {
	private String client_id;
	private String client_secret;
	private NaverOAuth2Operations naverOAuth2Operations;
	
	public NaverConnectionFactory(String client_id, String client_secret) {
		this.client_id = client_id;
		this.client_secret = client_secret;
		
		//팩토리에서 오퍼레이션으로 정보를 넘겨준다
		naverOAuth2Operations = new NaverOAuth2Operations();
		naverOAuth2Operations.setClient_id(client_id);
		naverOAuth2Operations.setClient_secret(client_secret);
	}
	
}
