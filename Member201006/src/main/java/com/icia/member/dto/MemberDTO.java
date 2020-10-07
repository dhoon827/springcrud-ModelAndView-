package com.icia.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	private String mid;
	private String mpassword;
	private String mname;
	private String mphone;
	private String memail;
	private String mbirth;
	
	private String kakaoId;
	private String naverId;
}
