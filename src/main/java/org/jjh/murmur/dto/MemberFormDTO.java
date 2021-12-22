package org.jjh.murmur.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberFormDTO {
	
	private Long memberId;
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String email;
	
	private String name;
	
	private String password;
	
	private String address;
	
	private String auth;
	
	private Boolean enabled;
	
	
	
	
	

}
