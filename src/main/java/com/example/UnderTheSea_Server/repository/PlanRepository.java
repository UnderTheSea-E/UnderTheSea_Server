package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Cacheable(value = "plans", key = "#user", cacheManager = "cacheManager", unless = "#userId == null")
    List<Plan> findByUserAndDate(User user, LocalDate date);

    //@Cacheable(value = "plan", key = "#plan_id", cacheManager = "cacheManager", unless = "#userId == null")
    Plan findByUserAndPlanId(User user, Long plan_id);

    @Modifying
    @Transactional
    @Query("UPDATE Plan p set p.title = :title, p.friend = :friend, p.content = :content, p.updated_at = :updated_at where p.planId = :plan_id")
    void updatePlan(
                    @Param("title") String title,
                    @Param("friend") Long friend,
                    @Param("content") String content,
                    @Param("updated_at") Timestamp updated_at,
                    @Param("plan_id") Long plan_id
    );
}
