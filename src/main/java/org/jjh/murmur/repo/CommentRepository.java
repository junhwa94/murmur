package org.jjh.murmur.repo;

import org.jjh.murmur.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
