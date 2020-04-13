package br.com.applogs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.applogs.model.Log;
import br.com.applogs.repository.LogRepository;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	
	@Autowired
	private LogRepository logRepository;

	public JobCompletionNotificationListener(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");
			Iterable<Log> logs = logRepository.findAll();
			while(logs.iterator().hasNext()) {
				System.out.println(logs.iterator().next());
			}
		}
	}
}
