package com.example.UnderTheSea_Server.model;
import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PostPlanReq {
    public Long friend_id;
    public Long recommend_id;
    public String content;
    public LocalDate date;
}
