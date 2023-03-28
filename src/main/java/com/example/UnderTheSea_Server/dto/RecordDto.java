package com.example.UnderTheSea_Server.dto;

import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.domain.PlanStatus;
import com.example.UnderTheSea_Server.domain.Record;
import com.example.UnderTheSea_Server.domain.RecordStatus;
import com.example.UnderTheSea_Server.domain.User;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Repository
public class RecordDto {
    private Timestamp created_at = new Timestamp(new Date().getTime());
    private Timestamp updated_at = new Timestamp(new Date().getTime());

    public Record insertRecord(User user, String img_url, int satisfaction, String content, LocalDate date) {
        Record recordEntity = Record.builder()
                .user(user)
                .img_url(img_url)
                .satisfaction(satisfaction)
                .content(content)
                .date(date)
                .status(RecordStatus.ACTIVE)
                .created_at(created_at)
                .updated_at(updated_at)
                .build();

        return recordEntity;
    }
}
