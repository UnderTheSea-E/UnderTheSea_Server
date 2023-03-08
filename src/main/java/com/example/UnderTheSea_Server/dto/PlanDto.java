package com.example.UnderTheSea_Server.dto;

import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.domain.PlanStatus;
import com.example.UnderTheSea_Server.domain.User;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Repository
public class PlanDto {
    private Timestamp created_at = new Timestamp(new Date().getTime());
    private Timestamp updated_at = new Timestamp(new Date().getTime());

    public Plan insertPlan(User user, String title, User freind, String content, LocalDate date) {
        Plan planEntity = Plan.builder()
                .user(user)
                .title(title)
                .friend(freind)
                .content(content)
                .date(date)
                .status(PlanStatus.ACTIVE)
                .created_at(created_at)
                .updated_at(updated_at)
                .build();
        return planEntity;
    }
}
