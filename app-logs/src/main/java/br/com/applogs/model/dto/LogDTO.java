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
	private String userAgent;
	

	public LogDTO() {
		
	}
	
	public LogDTO(String date, String ip, String request, Integer status, String userAgent) {
		this.date = date;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.userAgent =userAgent;
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
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
