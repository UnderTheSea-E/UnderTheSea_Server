package com.example.UnderTheSea_Server.model;

import com.example.UnderTheSea_Server.domain.Record;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

/* 서버>프론트 : 기록 정보 slect-list에 담아서 불러오기 */

public class GetRecordRes {
    public List<Record> records;
}
