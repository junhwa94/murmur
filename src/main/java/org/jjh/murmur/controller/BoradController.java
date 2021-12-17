package org.jjh.murmur.controller;

import org.jjh.murmur.dto.PageRequestDTO;
import org.jjh.murmur.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor  
public class BoradController {
	
	private final BoardService service;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
				
		model.addAttribute("result", service.getList(pageRequestDTO));
		
		
	}
	
	

}
