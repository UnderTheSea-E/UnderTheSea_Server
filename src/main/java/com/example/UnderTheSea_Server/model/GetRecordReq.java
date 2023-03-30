package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class GetRecordReq {
    public LocalDate date;
}
