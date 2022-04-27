package io.dvsgn.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobParameterTestConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public JobParameterTestConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job jobParameterTest() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("job instance test step1");
                    contribution.getStepExecution()
                            .getJobExecution()
                            .getJobParameters()
                            .getParameters()
                            .forEach((s, jobParameter) -> {
                                System.out.println("key = " + s);
                                System.out.println("param type = " + jobParameter.getType());
                                System.out.println("jobParameter = " + jobParameter);
                            });
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("job instance test step2");
                    chunkContext.getStepContext()
                            .getJobParameters()
                            .forEach((s, o) -> {
                                System.out.println("key = " + s);
                                System.out.println("o.getClass() = " + o.getClass());
                                System.out.println("object = " + o);
                            });
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
