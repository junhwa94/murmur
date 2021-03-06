package org.jjh.murmur.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
	
	private Long bno;
	
	private String writer;
	
	private String title;
	
	private String content;
	
	private int views;
	
	private Timestamp regDate;
	
	private Timestamp modDate;


}
