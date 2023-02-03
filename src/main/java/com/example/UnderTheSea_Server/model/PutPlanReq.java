package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PutPlanReq {
    public Long plan_id;
    public Long friend_id;
    public String content;
}
