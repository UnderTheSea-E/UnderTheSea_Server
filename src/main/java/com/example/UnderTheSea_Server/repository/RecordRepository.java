package com.example.UnderTheSea_Server.repository;

import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.domain.Record;
import com.example.UnderTheSea_Server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{

    List<Record> findByUserAndDate(User user, LocalDate date);

    Record findByUserAndRecordId(User user, Long record_id);

    @Modifying
    @Transactional
    @Query("UPDATE Record R set R.satisfaction = :satisfaction, R.content = :content, R.img_url = :img_url where R.recordId = :record_id and R.user = :user")
    void updateImgAndContent(@Param("img_url") String img_url,
                                @Param("content") String content,
                                @Param("record_id") Long plan_id,
                                @Param("satisfaction") Integer satisfaction
    );

}
