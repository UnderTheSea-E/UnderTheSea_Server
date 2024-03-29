package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class GetRecordRes {
    public Long record_id;
    public LocalDate date;
    public String content;
    public int satisfaction;
    //public String img_url;
}
