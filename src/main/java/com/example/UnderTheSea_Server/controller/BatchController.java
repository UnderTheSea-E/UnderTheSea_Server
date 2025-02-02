package com.example.UnderTheSea_Server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class BatchController {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    @Autowired
    private Job testJob;

    @Scheduled(cron = "0 0 12 * * ?")
    @GetMapping("/test/batch")
    public String testBatchJob(@RequestParam("purpose") String purpose) throws Exception {
        Date now = new Date();
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("purpose", purpose)
                .addDate("date", now)
                .toJobParameters();

        // 한 번 호출한 파라미터는 재사용 불가
        jobLauncher.run(testJob, jobParameters);

        return "batch job test";
    }
}
