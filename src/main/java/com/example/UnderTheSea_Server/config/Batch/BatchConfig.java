package com.example.UnderTheSea_Server.config.Batch;

import com.example.UnderTheSea_Server.domain.Place;
import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.*;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final DataSource dataSource;
    private final PlatformTransactionManager platformTransactionManager;

    private final PlanRepository planRepository;

    // JOB 정의
    @Bean
    public Job testJob() {
        return new JobBuilderFactory(jobRepository)
                .get("test job")
                .start(firstStep())
                //.next(secondStep())
                //.on(조건) - FAILED, COMPLETED, *
                //.to(nextStep()) - 조건에 만족할 경우 시행할 step
                .build();
    }

    // STEP 정의
    @Bean
    public Step firstStep() {
        return new StepBuilderFactory(jobRepository, platformTransactionManager)
                .get("first step")
                .<Plan, Plan>chunk(10)
                .reader(testReader())
                .processor(middleProcessor())
                .writer(testWriter())
                .build();
    }

    // Reader
    @Bean
    public RepositoryItemReader<Plan> testReader() {
        return new RepositoryItemReaderBuilder<Plan>()
                .name("testReader")
                .pageSize(10)
                .methodName("findAll")
                .repository(planRepository)
                .sorts(Map.of("plan_id", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public ItemReader<Place> crawlingReader() throws IOException {
        Connection connection = Jsoup.connect("https://map.kakao.com/?map_type=TYPE_MAP&folderid=4049789&target=other&page=bookmark");
        Document document = connection.get();
        Elements elements = document.select("div.directory_info");

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> positions = new ArrayList<>();

        return new ItemReader<Place>() {
            @Override
            public Place read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

                for(Element element: elements) {
                    String name = element.select("div.tit_directory").text();
                    names.add(name);
                    String position = element.select("div.desc_directory").text();
                    positions.add(position);
                    Place place = new Place(name, position);
                    return place;
                }
                return null;
            }
        };
    }

    // Processor
    @Bean
    public ItemProcessor<Plan, Plan> middleProcessor() {
        return new ItemProcessor<Plan, Plan>() {
            @Override
            public Plan process(Plan plan) throws Exception {
                Plan replication = new Plan();
                replication.setContent(plan.getContent());
                return plan;
            }
        };
    }

    // Writer
    @Bean // RepositoryItemWriter<[저장될 엔티티]>
    public RepositoryItemWriter<Plan> testWriter() {

        return new RepositoryItemWriterBuilder<Plan>()
                .repository(planRepository) // afterRepository를 통해서
                .methodName("save")          // save 쿼리 실행
                .build();
    }
}
