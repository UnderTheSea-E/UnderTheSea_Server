package com.example.UnderTheSea_Server.model;

import com.example.UnderTheSea_Server.domain.PlanStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class GetPlanRes {
    public Long planId;
    public String title;
    public Long freind;
    public String content;
    public LocalDate date;
    public PlanStatus status;
}
