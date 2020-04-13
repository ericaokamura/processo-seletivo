package br.com.applogs.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.applogs.model.Log;


@Component
public class LogDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6240033378583717094L;
	private String date;
	private String ip;
	private String request;
	private Integer status;
	private String user_agent;
	

	public LogDTO() {
		
	}
	
	public LogDTO(String date, String ip, String request, Integer status, String user_agent) {
		this.date = date;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.user_agent =user_agent;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
}
