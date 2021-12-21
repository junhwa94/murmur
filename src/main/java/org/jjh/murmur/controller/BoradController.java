package org.jjh.murmur.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import com.google.gson.JsonObject;

import org.apache.commons.io.FileUtils;
import org.jjh.murmur.dto.BoardDTO;
import org.jjh.murmur.dto.PageRequestDTO;
import org.jjh.murmur.repo.BoardRepository;
import org.jjh.murmur.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor  
public class BoradController {
	
	private final BoardService service;
	
//	@Autowired
//	BoardRepository repo;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
				
		model.addAttribute("result", service.getList(pageRequestDTO));

	}
	
	@GetMapping({"/content", "/modifyForm"})
	public void content(long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		
		BoardDTO dto = service.content(bno);
		
//		repo.updateViews(bno);
		
		model.addAttribute("dto",dto);
		
//		return "board/content";
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
		
		service.modify(dto);
		
		redirectAttributes.addAttribute("page", requestDTO.getPage());
		redirectAttributes.addAttribute("bno", dto.getBno());
		
		return "redirect:/board/content";
	}
	
	@GetMapping("/insertForm")
	public String boardForm() {

		return "board/insertForm";

	}
	
	// 게시물 등록
    @PostMapping("/insertForm")
    @ResponseBody
    public String insertBoard(BoardDTO dto){

    	service.insertBoard(dto);

        String test = "Success";
        
        return test;
        
    }
	
	// 썸머 노트 file 컨트롤러
    @PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }
    
    @GetMapping("/delete")
    public String deleteBoard(long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO) {
    	
    	service.delete(bno);
    	
    	return "redirect:/board/list";
    	
    }
    

	


}
