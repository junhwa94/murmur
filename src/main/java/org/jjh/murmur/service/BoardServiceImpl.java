package org.jjh.murmur.service;

import java.util.function.Function;

import org.jjh.murmur.dto.BoardDTO;
import org.jjh.murmur.dto.PageRequestDTO;
import org.jjh.murmur.dto.PageResultDTO;
import org.jjh.murmur.entity.Board;
import org.jjh.murmur.repo.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository repo;
	
	@Override
	public PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO){
		
		Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
		
		Page<Board> result = repo.findAll(pageable);
		
		Function<Board, BoardDTO> fn = (entity -> entityToDto(entity));
		
		return new PageResultDTO<>(result, fn);
		
		
	}

	@Override
	public Long insertBoard(BoardDTO dto) {
		
		Board entity = dtoToEntity(dto);
		
		repo.save(entity);
		
		return entity.getBno();
	}

}
