package org.jjh.murmur.service;

import org.jjh.murmur.entity.Member;

import org.jjh.murmur.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{

   
    private final MemberRepository memberRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    
    public Member saveMember(Member member) {
    	
    	validateDuplicateMember(member);
    	
    	return memberRepo.save(member);
    	
    }
    
    private void validateDuplicateMember(Member member) {
    	
    	Member findMember = memberRepo.findByEmail(member.getEmail());
    	
    	if(findMember != null) {
    		throw new IllegalStateException("이미 가입된 회원입니다.");
    	}
    	
    }
    // 유저 이메일을 통해서 중복가입인지 검사
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Member member = memberRepo.findByEmail(email);
		
		if(member == null) {
			
			throw new UsernameNotFoundException(email);
			
		}
		
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getAuth())
				.build();
	}


}
