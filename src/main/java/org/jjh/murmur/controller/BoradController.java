package org.jjh.murmur.controller;

import java.lang.ProcessBuilder.Redirect;

import org.jjh.murmur.dto.BoardDTO;
import org.jjh.murmur.dto.PageRequestDTO;
import org.jjh.murmur.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@GetMapping("/content")
	public String content(long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		
		BoardDTO dto = service.content(bno);
		
		model.addAttribute("dto",dto);
		
		return "redirect:/board/content";
		
	}
	


}
