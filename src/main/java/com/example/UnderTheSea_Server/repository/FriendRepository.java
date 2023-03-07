package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {




    
}
