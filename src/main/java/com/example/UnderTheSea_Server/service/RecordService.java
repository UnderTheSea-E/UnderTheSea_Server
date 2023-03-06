package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.domain.Record;
import com.example.UnderTheSea_Server.dto.RecordDto;
import com.example.UnderTheSea_Server.model.PutRecordReq;
import com.example.UnderTheSea_Server.model.PutRecordRes;
import com.example.UnderTheSea_Server.repository.RecordRepository;
import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.model.PostRecordReq;
import com.example.UnderTheSea_Server.model.PostRecordRes;
import com.example.UnderTheSea_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor

public class RecordService {

    public final RecordRepository RecordRepository;
    public final Record record;
    public final RecordDto recordDto;
    public final UserRepository userRepository;
    public final PutRecordReq putRecordReq;
    public final PutRecordRes putRecordRes;

    //기록 저장하기
    @Transactional
    public PostRecordRes createRecord(PostRecordReq postRecordReq) throws BaseException {
        try{
            //기록 db에 insert하기
            Record record = RecordRepository.save(
                    recordDto.insertRecord(
                            postRecordReq.img_url,
                            postRecordReq.content,
                            postRecordReq.satisfaction
                    ));

            return new PostRecordRes(record.getRecord_id());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

        public PutRecordRes updateRecords(PutRecordReq putRecordReq) throws BaseException {
            try{

                //계획 db에서 update하기
                RecordRepository.updateImgAndContent(
                        putRecordReq.content,
                        putRecordReq.record_id,
                        putRecordReq.satisfaction
                );

                return new PutRecordRes(putRecordReq.record_id);
            } catch (Exception exception) {
                throw new BaseException(DATABASE_ERROR);
            }
    }
}
