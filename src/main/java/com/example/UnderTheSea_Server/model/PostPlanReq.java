package com.example.UnderTheSea_Server.model;
import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PostPlanReq {
    public String title;
    public Long friend_id;
    public String content;
    public LocalDate date;
}
