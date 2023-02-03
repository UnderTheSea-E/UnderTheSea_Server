package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByUserAndDate(User user, LocalDate date);

    Plan findByUserAndPlanId(User user, Long plan_id);

    @Modifying
    @Transactional
    @Query("UPDATE Plan p set p.friend = :friend, p.content = :content where p.planId = :plan_id and p.user = :user")
    void updateFriendAndContent(@Param("friend") User friend,
                    @Param("content") String content,
                    @Param("plan_id") Long plan_id,
                    @Param("user") User user
    );

    /*
    @Modifying
    @Transactional
    @Query(value = "UPDATE Plan p set p.friend_id = :friend and p.content = :content where p.plan_id = :plan_id and p.user_id = :user", nativeQuery = true)
    Plan updatePlan(User user, long plan_id, long friend_id, String content);
     */
}
