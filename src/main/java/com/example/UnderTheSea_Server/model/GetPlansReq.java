package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//달력의 날짜를 누르면 그 날짜에 대한 계획들이 나오는 것
//상세기록도 가져와야해서 더 받아와야할 듯

@NoArgsConstructor
@AllArgsConstructor
public class GetPlansReq {
    public LocalDate date;
}
