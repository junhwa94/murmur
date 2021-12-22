package org.jjh.murmur.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jjh.murmur.dto.MemberFormDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;


//@NoArgsConstructor(access = AccessLevel.PROTECTED) //다른 패키지에서 생성자 함부로 생성금지, 추후 사용


@Entity
@Data
@Table(name = "member")
public class Member {
	
	
	 @Id
	 @Column(name = "member_id")	// 기본키(유저 넘버)
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long memberId;
	 
	 @Column(unique = true)
	 private String email;
	 
	 private String name;
	 
	 private String password;
	 
	 private String address;
	 
	 private String auth;
	 
	 private Boolean enabled;
	 
	 // entity Member를 MemberDTO 변환 
	 public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder) {
		 
		 Member member = new Member();
		 
		 member.setName(memberFormDTO.getName());
		 member.setEmail(memberFormDTO.getEmail());
		 member.setAddress(memberFormDTO.getAddress());
		 
		 String password = passwordEncoder.encode(memberFormDTO.getPassword());
		 
		 member.setPassword(password);
		 member.setAuth("USER");
//		 member.setAuth(memberFormDTO.getAuth());
		 member.setEnabled(true);
		 
		 return member;
		 
	 }

	 

}
