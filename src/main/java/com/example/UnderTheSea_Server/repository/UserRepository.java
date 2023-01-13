package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Jpa Naming 전략
    // SELECT * FROM member WHERE id = ?
    // Insert into member ( user_id, password, user_name, createdate, email, role) values ( 'hermeswing', '$2a$10$C4BZPH4raAlKGrvy9dtyyufBp56af2W6fge0hD1wLctWvNEjrK.AG', '홍길동', now(), 'hermeswing@test.com', 'ROLE_ADMIN')
    // id:1, pw:1234
    User findByUserId(String userId); // JPA Query Method

    Optional<Object> findByUserEmail(String kakaoEmail);
}
