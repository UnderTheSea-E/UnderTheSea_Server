package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.Record;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.dto.RecordDto;
import com.example.UnderTheSea_Server.model.*;
import com.example.UnderTheSea_Server.repository.RecordRepository;
import com.example.UnderTheSea_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final UserRepository userRepository;

    private final RecordRepository recordRepository;

    private final RecordDto recordDto;

    //기록 저장하기
    //@Transactional
    public PostRecordRes createRecord(Long userIdByJwt, PostRecordReq postRecordReq) throws BaseException {
        try {
            //현재 로그인한 사용자 불러오기
            User user = userRepository.getById(userIdByJwt);
            //친구 불러오기
            //User friend = userRepository.getById(postPlanReq.friend);

            //계획 db에 insert하기
            Record record = recordRepository.save(
                    recordDto.insertRecord(
                            user,
                            postRecordReq.img_url,
                            postRecordReq.satisfaction,
                            postRecordReq.content,
                            postRecordReq.date));

            return new PostRecordRes(record.getRecord_id());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //기록들 조회하기
    //@Transactional
    public GetRecordsRes selectRecords(User userByJwt, LocalDate date) throws BaseException {
        try {
            //계획 db에서 select하기
            List<Record> records = recordRepository.findByUserAndDate(userByJwt, date);


            return new GetRecordsRes(records);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //기록 상세 조회하기
    public GetRecordRes selectRecord(User userByJwt, Long record_id) throws BaseException {
        try {
            //계획 db에서 select하기
            Record record = recordRepository.findById(record_id).get();

            return new GetRecordRes(record.getRecord_id(), record.getDate(), record.getContent(), record.getSatisfaction());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
