package br.com.applogs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.applogs.model.Log;
import br.com.applogs.repository.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private LogRepository logRepository;
		
	public LogService (LogRepository logRepository) {
		this.logRepository = logRepository;
	}
	
	public Iterable<Log> listAllLogs(){
		return logRepository.findAll();
	}
	
	public Log saveLog(Log log) {
		return logRepository.saveAndFlush(log);
	}

	public Iterable<Log> listLogsByIp(String ip) {
		return logRepository.findByIp(ip);
	}

	public Iterable<Log> listLogsByDate(String startTime, String endTime) {
		return logRepository.findByDateBetween(LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
	}

	public Iterable<Log> listLogsByIpAndDate(String ip, String startTime, String endTime) {
		return logRepository.findByIpAndDateBetween(ip, LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
	}
	
	public Integer countByIpAndDateAndUserAgent(String ip, String startTime, String endTime, String userAgent) {
		return logRepository.findByIpAndDateBetweenAndUserAgent(ip, LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), userAgent).size();
	}

}
