package com.example.UnderTheSea_Server.model;

import com.example.UnderTheSea_Server.domain.Record;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class GetRecordsRes {
    public List<Record> records;
}
