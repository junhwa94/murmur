package org.jjh.murmur.controller;



import javax.validation.Valid;

import org.jjh.murmur.dto.MemberFormDTO;
import org.jjh.murmur.entity.Member;
import org.jjh.murmur.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
    @GetMapping("/login")
    public String login() {
    	
        return "member/login";
    }
    
    // 회원가입 페이지 진입
    @GetMapping("/joinForm")
    public String joinForm(Model model) {
    	
    	model.addAttribute("memberFormDTO", new MemberFormDTO());
    	
        return "member/joinForm";
    }
    

	@PostMapping("/joinForm")
	public String join(@Valid MemberFormDTO memberFormDTO, BindingResult bindingResult, Model model) { 
		
		try {
			Member member = Member.createMember(memberFormDTO, passwordEncoder);
			memberService.saveMember(member);
			
		} catch (IllegalStateException e) {
			
			model.addAttribute("errorMessage", e.getMessage()); 
			
			return "member/joinForm";
		}
		
		return "redirect:/"; 
	}
	 
}
