package org.jjh.murmur.service;

import org.jjh.murmur.dto.BoardDTO;
import org.jjh.murmur.dto.PageRequestDTO;
import org.jjh.murmur.dto.PageResultDTO;
import org.jjh.murmur.entity.Board;

// 서비스단을 중신을 db와 서비스는 엔티티 :::::: 서비스와 컨트롤러는 dto
public interface BoardService {
	
	Long insertBoard(BoardDTO dto);
	
	default Board dtoToEntity(BoardDTO dto) {	// dto를 엔티티로 변환
		
		Board entity = Board.builder()
				.bno(dto.getBno())
				.writer(dto.getWriter())
				.title(dto.getTitle())
				.content(dto.getContent())
				.views(dto.getViews())
				.regDate(dto.getRegDate())
				.build();
		
		return entity;	
	}
	
	default BoardDTO entityToDto(Board entity) {	// 엔티티를 dto로 변환
		
		BoardDTO dto = BoardDTO.builder()
				.bno(entity.getBno())
				.writer(entity.getWriter())
				.title(entity.getTitle())
				.content(entity.getContent())
				.views(entity.getViews())
				.regDate(entity.getRegDate())
				.build();	
		
		return dto;
		
	
	}

	PageResultDTO<BoardDTO, Board> getList(PageRequestDTO pageRequestDTO);

	BoardDTO content(long bno);
	
	

}
