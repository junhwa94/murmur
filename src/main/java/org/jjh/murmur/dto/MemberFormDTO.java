package org.jjh.murmur.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberFormDTO {
	
	private Long memberId;
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String email;
	
	@NotBlank(message = "닉네임 or 이름은 필수 입력 값입니다.")
	private String name;
	
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
	private String password;
	
	private String address;
	
	private String auth;
	
	private Boolean enabled;
	
	
	
	
	

}
