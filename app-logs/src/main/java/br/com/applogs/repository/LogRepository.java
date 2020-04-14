package br.com.applogs.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.applogs.model.Log;

@Repository
public interface LogRepository extends BaseRepository<Log, UUID> {
	
	public Iterable<Log> findByIp(String ip);
	public Iterable<Log> findByDateBetween(LocalDateTime startTime, LocalDateTime endTime);
	public Iterable<Log> findByIpAndDateBetween(String ip, LocalDateTime startTime, LocalDateTime endTime);
	public List<Log> findByIpAndDateBetweenAndUserAgent(String ip, LocalDateTime startTime, LocalDateTime endTime, String userAgent);

}

