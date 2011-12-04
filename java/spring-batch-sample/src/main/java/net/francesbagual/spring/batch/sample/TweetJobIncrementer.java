package net.francesbagual.spring.batch.sample;

import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class TweetJobIncrementer implements JobParametersIncrementer {

    public static String RUN_ID="runId";
    public static String EXECUTION_DATE="executionDate";
    
	@Override
	public JobParameters getNext(JobParameters parameters) {
		long id=0;
        if (parameters==null || parameters.isEmpty()) {
            id=1;
        }else{
            id = parameters.getLong(RUN_ID,1L) + 1;
        }
        JobParametersBuilder builder = new JobParametersBuilder();
 
        builder.addLong(RUN_ID, id).toJobParameters();
        builder.addLong("EXECUTION_DATE", new Date().getTime());
 
        return builder.toJobParameters();
	}

}
