package com.example.UnderTheSea_Server.dto;

import com.example.UnderTheSea_Server.domain.Record;
import com.example.UnderTheSea_Server.domain.*;
import org.springframework.stereotype.Repository;

import java.lang.Record;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Repository
public class RecordDto {
    private Timestamp created_at = new Timestamp(new Date().getTime());
    private Timestamp updated_at = new Timestamp(new Date().getTime());

    public Record insertRecord(String img_url, String content) {
        com.example.UnderTheSea_Server.domain.Record recordEntity = Record.builder()
                .img_url(img_url)
                .content(content)
                .satisfaction(satisfaction)
                .status(RecordStatus.ACTIVE)
                .created_at(created_at)
                .updated_at(updated_at)
                .build();
        return recordEntity;
    }

