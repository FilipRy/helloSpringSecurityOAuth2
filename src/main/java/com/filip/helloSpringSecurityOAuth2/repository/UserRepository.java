package com.filip.helloSpringSecurityOAuth2.repository;

import com.filip.helloSpringSecurityOAuth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
