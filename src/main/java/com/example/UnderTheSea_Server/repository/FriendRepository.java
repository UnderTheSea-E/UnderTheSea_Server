package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByUser1(User user);
}
