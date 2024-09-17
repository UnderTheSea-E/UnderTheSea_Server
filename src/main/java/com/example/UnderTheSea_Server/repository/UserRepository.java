package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.User;
import org.springframework.cache.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    //@Cacheable(value = "kakao_user_id", key = "#userId", cacheManager = "cacheManager", unless = "#userId == null")
    //@CacheEvict(value = "kakao_user_id", key = "#userId", cacheManager = "cacheManager")
    @CachePut(value = "kakao_user_id", key = "#userId", cacheManager = "cacheManager", unless = "#userId == null")
    User findByUserId(Long userId); // JPA Query Method

    //@Cacheable(value = "kakao_user_email", key = "#email", cacheManager = "cacheManager", unless = "#userId == null")
    User findByEmail(String email);

    //@CachePut(key = "#user_id")
    //@CacheEvict(key = "#user_id")
    @Modifying
    @Transactional
    @Query("UPDATE User u set u.characterId = :character_id, u.characterName = :character_name, u.updated_at = :updated_at where u.userId = :user_id")
    void updateCharacter(@Param("character_id") Long character_id,
                                @Param("character_name") String character_name,
                                @Param("updated_at") Timestamp updated_at,
                                @Param("user_id") Long user_id
    );

    @Modifying
    @Transactional
    @Query("UPDATE User u set u.mileage = :mileage, u.updated_at = :updated_at where u.userId = :user_id")
    void updateMileage(@Param("mileage") Long mileage,
                           @Param("updated_at") Timestamp updated_at,
                           @Param("user_id") Long user_id
    );
}

