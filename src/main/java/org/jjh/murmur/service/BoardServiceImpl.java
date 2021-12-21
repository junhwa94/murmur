package org.jjh.murmur.service;

import java.util.Optional;
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
@RequiredArgsConstructor // 의존성 자동 주입
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository repo;
	
	@Override
	public PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO){
		
		Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
		
		Page<Board> result = repo.findAll(pageable);
		
		Function<Board, BoardDTO> fn = (entity -> entityToDto(entity));
		
		return new PageResultDTO<>(result, fn);

	}
	
	// 방명록 등록
	@Override
	public Long insertBoard(BoardDTO dto) {
		
		Board entity = dtoToEntity(dto);
		
		repo.save(entity);
		
		return entity.getBno();
	}
	
	// 방명록 조회
	@Override
	public BoardDTO content(long bno) {
		
		Optional<Board> result = repo.findById(bno);
		
		return result.isPresent()? entityToDto(result.get()): null;
	}
	
	// 방명록 삭제
	@Override
	public void delete(long bno) {
	
		repo.deleteById(bno);		
	}
	
	@Override
	public void modify(BoardDTO dto) {
		
		Optional<Board> result = repo.findById(dto.getBno());
		if(result.isPresent()) {
			
			Board entity = result.get();
			
			entity.changeTitle(dto.getTitle());
	        entity.changeContent(dto.getContent());
	        
	        repo.save(entity);
		}
		
	}



}
