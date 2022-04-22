package io.dvsgn.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public DBJobConfiguration(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job dbSchemaErrorTestJob() {
        return jobBuilderFactory.get("dbSchemaErrorTestJob")
                .start(errorOccurStep())
                .build();
    }

    public Step errorOccurStep() {
        return stepBuilderFactory.get("errorOccurStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("error occur?");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
