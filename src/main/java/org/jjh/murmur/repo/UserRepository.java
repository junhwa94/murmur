package org.jjh.murmur.repo;

import org.jjh.murmur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
