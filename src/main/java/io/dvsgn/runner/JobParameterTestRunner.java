package io.dvsgn.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class JobParameterTestRunner implements ApplicationRunner {
    private final Job job;
    private final JobLauncher jobLauncher;

    public JobParameterTestRunner(Job jobParameterTest, JobLauncher jobLauncher) {
        this.job = jobParameterTest;
        this.jobLauncher = jobLauncher;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jobLauncher.run(job, new JobParametersBuilder()
                .addString("string type", "test")
                .addDate("date type", new Date())
                .addDouble("double type", 5.5)
                .toJobParameters());
        System.out.println(Arrays.toString(args.getSourceArgs()));
        System.out.println("args.getOptionValues(\"customOpt\") = " + args.getOptionValues("customOpt"));
        System.out.println(args.getOptionNames().toString());
    }
}
