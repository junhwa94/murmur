package org.jjh.murmur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {


    @GetMapping("/")
    public String index(){
        return "/home";
    }

	/*
	 * @GetMapping("/regCategoryForm") public String regCategory(){ return
	 * "board/regCategoryForm"; }
	 */

}
