package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserId(Long userId); // JPA Query Method

    User findByEmail(String kakaoEmail);

    @Modifying
    @Transactional
    @Query("UPDATE User u set u.characterId = :character_id, characterName = :character_name where u.userId = :user_id")
    void updateCharacterId(@Param("character_id") Long character_id,
                                @Param("character_name") String character_name,
                                @Param("user_id") Long user_id
    );
}

