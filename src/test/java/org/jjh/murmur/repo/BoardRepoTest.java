package org.jjh.murmur.repo;

import java.util.stream.IntStream;

import org.jjh.murmur.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardRepoTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	
	@Test
	public void insertBoard() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			
			Board board = Board.builder()
					.writer("작성자" + i)
					.title("테스트 제목" + i)
					.content("테스트 내용입니다." + i)
					.build();
			boardRepo.save(board);
					
			
		});
		
	}

}
