package br.com.applogs.model;

import java.time.LocalDateTime;

public class LogAggregationResult {
	
	private String ip;
	
	private LocalDateTime date;
	
	private String user_agent;
	
	public LogAggregationResult() {
	
	}

	public LogAggregationResult(String ip, LocalDateTime date, String user_agent) {
		this.ip = ip;
		this.date = date;
		this.user_agent = user_agent;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	
	

}
