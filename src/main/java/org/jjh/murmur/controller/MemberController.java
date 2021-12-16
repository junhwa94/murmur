package org.jjh.murmur.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/member")
public class MemberController {



    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/join")
    public String register() {
        return "member/joinform";
    }

	/*
	 * @PostMapping("/register") public String register(User user) {
	 * userService.save(user); return "redirect:/"; }
	 */
}
