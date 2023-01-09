package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
