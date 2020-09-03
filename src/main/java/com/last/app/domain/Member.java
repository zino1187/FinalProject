package com.last.app.domain;

import lombok.Data;

@Data
public class Member {
	private int member_id;
	private String sns_id;
	private String email;
	private String regdate;
}
