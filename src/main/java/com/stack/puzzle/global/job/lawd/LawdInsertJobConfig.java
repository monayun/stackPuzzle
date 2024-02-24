package com.stack.puzzle.global.job.lawd;

import com.stack.puzzle.domain.house.consts.LawdType;
import com.stack.puzzle.domain.house.dto.LawdRequest;
import com.stack.puzzle.domain.house.service.LawdService;
import com.stack.puzzle.global.job.validator.FilePathParameterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
@RequiredArgsConstructor
@Slf4j
public class LawdInsertJobConfig {

    private final LawdService lawdService;

    @Bean("lawdInsertJob")
    public Job lawdInsertJob(JobRepository jobRepository, Step lawdInsertStep){
        return new JobBuilder("lawdInsertJob", jobRepository)
                .incrementer(new RunIdIncrementer())    // job 실행시 횟수 일정하게 증가
                .validator(new FilePathParameterValidator())
                .start(lawdInsertStep)
                .build();

    }

    @JobScope   // job이 살아있을때만 동작
    @Bean("lawdInsertStep")
    public Step lawdInsertStep(JobRepository jobRepository, FlatFileItemReader<LawdRequest> lawdFileItemReader,
                         ItemWriter<LawdRequest> lawdItemWriter, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("lawdInsertStep", jobRepository)
                .<LawdRequest, LawdRequest>chunk(1000, platformTransactionManager)
                .reader(lawdFileItemReader)
                .writer(lawdItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<LawdRequest> lawdFileItemReader(@Value("#{jobParameters['filePath']}") String filePath) {
        return new FlatFileItemReaderBuilder<LawdRequest>()
                .name("lawdFileItemReader")
                .delimited()
                .delimiter("\t")    // tab으로 구분
                .names(LawdType.LAWD_CD.getName(), LawdType.LAWD_DONG.getName(), LawdType.EXIST.getName())
                .linesToSkip(1) // 첫번째 줄 헤더 건너뛰어줘
                .fieldSetMapper(new LawdFieldSetMapper())
                .resource(new ClassPathResource(filePath))
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<LawdRequest> lawdItemWriter() {
        return items -> items.forEach(lawdService::upsert);
    }
}
