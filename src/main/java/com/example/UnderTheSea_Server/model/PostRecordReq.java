package com.example.UnderTheSea_Server.model;

import com.example.UnderTheSea_Server.domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
public class PostRecordReq {
    public String img_url;
    public Integer satisfaction;
    public String content;
}
