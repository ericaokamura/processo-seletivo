package br.com.applogs.step;


import org.springframework.batch.item.ItemProcessor;

import br.com.applogs.model.Log;
import br.com.applogs.model.converter.LogConverter;
import br.com.applogs.model.dto.LogDTO;

public class Processor implements ItemProcessor<LogDTO, Log> {
	
	@Override
	public Log process(LogDTO logDTO) throws Exception {
		return LogConverter.DTOToModel(logDTO);
	}
}
