package org.jjh.murmur.repo;

import org.jjh.murmur.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Member findByEmail(String email);


}
