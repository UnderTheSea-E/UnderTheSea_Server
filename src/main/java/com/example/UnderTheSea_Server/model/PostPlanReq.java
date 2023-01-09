package com.example.UnderTheSea_Server.model;
import java.util.Date;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostPlanReq {
    public Long friend_id;
    public Long recommend_id;
    public String content;
    public Date date;
}
