package org.jjh.murmur.controller;


import org.jjh.murmur.model.User;
import org.jjh.murmur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private UserService userService;
	
	
    @GetMapping("/login")
    public String login() {
    	
        return "member/login";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
    	
        return "member/joinForm";
    }

	
	@PostMapping("/joinForm") 
	public String memberJoin(User user) {
		
		userService.save(user); 
		
		return "redirect:/"; 
	}
	 
}
