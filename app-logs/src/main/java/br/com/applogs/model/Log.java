package br.com.applogs.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="LOG")
@Table(name="LOG")
public class Log {
	
	@Id
	private String id;
	private LocalDateTime date;
	private String ip;
	private String request;
	private Integer status;
	@Column(name="user_agent")
	private String userAgent;
	
	public Log() {
		
	}

	public Log(String id, LocalDateTime date, String ip, String request, Integer status, String userAgent) {
		
		this.id = id;
		this.date = date;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.userAgent = userAgent;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUser_agent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public String toString() {
		return "Log de ID: " + this.id + ", data: " + this.date + ", IP: " + this.ip + 
				", request: " + this.request + ", status: " + this.status + ", User Agent: " + this.userAgent + ".";
	}
	
}
