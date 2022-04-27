package io.dvsgn.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobExecutionTestConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public JobExecutionTestConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job jobExecutionTest() {
        return jobBuilderFactory.get("job")
                .start(executionStep1())
                .build();
    }

    public Step executionStep1() {
        return stepBuilderFactory.get("executionStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("job execution test step1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
