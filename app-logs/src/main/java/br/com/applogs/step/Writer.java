package br.com.applogs.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.applogs.model.Log;
import br.com.applogs.model.converter.LogConverter;
import br.com.applogs.repository.LogRepository;

public class Writer implements ItemWriter<Log> {
	
	@Autowired
	private LogRepository logRepository;
	
	public Writer(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public void write(List<? extends Log> logs) throws Exception {
		for (int i = 0; i < logs.size(); i++) {
			logRepository.save(logs.get(i));
		}
	}

}
