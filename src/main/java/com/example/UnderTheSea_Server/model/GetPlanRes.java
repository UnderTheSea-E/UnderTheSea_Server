package com.example.UnderTheSea_Server.model;

import com.example.UnderTheSea_Server.domain.Plan;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class GetPlanRes {
    public List<Plan> plans;
}
