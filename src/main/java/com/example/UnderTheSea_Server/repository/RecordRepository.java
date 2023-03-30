package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.domain.Record;
import com.example.UnderTheSea_Server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByUserAndDate(User user, LocalDate date);
}
