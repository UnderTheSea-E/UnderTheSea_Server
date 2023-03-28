package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PutPlanReq {
    public Long planId;
    public Long friend;
    public String title;
    public String content;
}
