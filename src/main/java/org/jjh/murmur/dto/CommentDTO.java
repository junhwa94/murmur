package org.jjh.murmur.dto;

import java.sql.Timestamp;

import org.jjh.murmur.entity.Board;
import org.jjh.murmur.entity.Member;

public class CommentDTO {
	
	private Long cmNo;
	
	private Board board;
	
	private int cmGrp;
	
	private String cmContent;
	
	private Member member;
	
	private Timestamp cmRegdate;

}
