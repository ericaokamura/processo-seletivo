package br.com.applogs.model.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.applogs.model.Log;
import br.com.applogs.model.dto.LogDTO;

@Component
public class LogConverter {
	
	public static Log DTOToModel(LogDTO logDTO) {
		return new Log(UUID.randomUUID().toString(), LocalDateTime.parse(logDTO.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), logDTO.getIp(), logDTO.getRequest(), logDTO.getStatus(), logDTO.getUser_agent());
	}
	
	public static LogDTO modelToDTO(Log log) {
		return new LogDTO(log.getDate().toString(), log.getIp(), log.getRequest(), log.getStatus(), log.getUser_agent());
	}

}
