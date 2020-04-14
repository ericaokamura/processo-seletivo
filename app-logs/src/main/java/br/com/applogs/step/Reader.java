package br.com.applogs.step;

import java.nio.charset.Charset;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import br.com.applogs.model.dto.LogDTO;

public class Reader implements ItemReader<LogDTO>, ItemStream {
	
	private FlatFileItemReader<LogDTO> delegate;

	@Override
	public LogDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return this.delegate.read();
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter("|");
		tokenizer.setNames(new String[] { "date", "ip", "request", "status", "userAgent" });

		DefaultLineMapper<LogDTO> lineMapper = new DefaultLineMapper<LogDTO>();
		lineMapper.setLineTokenizer(tokenizer);
		
		BeanWrapperFieldSetMapper<LogDTO> mapper = new BeanWrapperFieldSetMapper<LogDTO>();
		mapper.setTargetType(LogDTO.class);
		lineMapper.setFieldSetMapper(mapper);
		
		this.delegate = new FlatFileItemReaderBuilder<LogDTO>().name("logItemReader")
				.encoding(Charset.defaultCharset().name())
				.resource(new ClassPathResource("access.log")).delimited()
				.names(new String[] { "date", "ip", "request", "status", "userAgent" })
				.lineMapper(lineMapper)
				.build();
		
		this.delegate.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		this.delegate.update(executionContext);
	}

	@Override
	public void close() throws ItemStreamException {
		this.delegate.close();	
	}

}
