package org.jjh.murmur.service;

import java.util.List;

import org.jjh.murmur.entity.Comment;

public interface CommentService {
	
	// 댓글 등록
	Comment insert(Comment comment);
	
	// 댓글 조회
	List<Comment> getCommentList(Long bno);
	
	// 댓글 group number 조회
	Integer getLastCommentGroup(Long bno);

}
