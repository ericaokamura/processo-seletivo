package br.com.applogs.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.applogs.model.Log;
import br.com.applogs.model.dto.LogDTO;
import br.com.applogs.repository.LogRepository;
import br.com.applogs.step.Processor;
import br.com.applogs.step.Reader;
import br.com.applogs.step.Writer;

@Configuration
@EnableBatchProcessing
@ComponentScan("br.com.applogs")
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public LogRepository logRepository;
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<LogDTO, Log>chunk(50).allowStartIfComplete(true).listener(new JobCompletionNotificationListener(logRepository)).reader(new Reader()).processor(new Processor())
				.writer(new Writer(logRepository)).build();
	}
}
