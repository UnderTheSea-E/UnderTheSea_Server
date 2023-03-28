package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class PostRecordReq {
    public LocalDate date;
    public String content;
    public String img_url;
    public int satisfaction;
}
