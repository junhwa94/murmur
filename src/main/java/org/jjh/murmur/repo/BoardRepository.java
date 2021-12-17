package org.jjh.murmur.repo;

import org.jjh.murmur.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
