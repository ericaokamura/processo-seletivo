package br.com.applogs.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.applogs.model.Log;
import br.com.applogs.model.dto.LogDTO;
import br.com.applogs.service.LogService;

@RunWith(MockitoJUnitRunner.class)
public class LogControllerTests {
	
	@InjectMocks
	private LogController controller;
	
	@Mock
	private LogService service;
	
	private List<Log> logs;
	
	private LogDTO dto;
	
	@Before 
	public void setUp() {
		LocalDateTime date1 = LocalDateTime.parse("2020-04-12 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		Log log1 = new Log(UUID.randomUUID().toString(), date1, "192.168.234.82", "GET / HTTP/1.1", 200, "Google Chrome");
		LocalDateTime date2 = LocalDateTime.parse("2020-04-13 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		Log log2 = new Log(UUID.randomUUID().toString(), date2, "192.168.169.82", "GET / HTTP/1.1", 200, "Google Chrome");
		LocalDateTime date3 = LocalDateTime.parse("2020-04-13 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		Log log3 = new Log(UUID.randomUUID().toString(), date3, "192.168.234.82", "GET / HTTP/1.1", 200, "Google Chrome");
		LocalDateTime date4 = LocalDateTime.parse("2020-04-12 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		Log log4 = new Log(UUID.randomUUID().toString(), date4, "192.168.169.82", "GET / HTTP/1.1", 200, "Google Chrome");
		logs = new ArrayList<>();
		logs.add(log1);
		logs.add(log2);
		logs.add(log3);
		logs.add(log4);
		
		dto = new LogDTO("2020-04-12 00:00:00.000", "192.168.234.82", "GET / HTTP/1.1", 200, "Google Chrome");
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void test_list_all_logs() {
		when(this.service.listAllLogs()).thenReturn(logs);
		List<Log> list = new ArrayList<Log>();
		Iterator<Log> iterator = this.controller.listAllLogs().getBody().iterator();
		while (iterator.hasNext()) {
		    list.add(iterator.next());
		}
		assertEquals(list.size(), logs.size());
	}
	
	@Test
	public void test_list_logs_by_ip() {
		List<Log> logs_ip =  new ArrayList<Log>();
		logs_ip.add(logs.get(0));
		logs_ip.add(logs.get(1));
		when(this.service.listLogsByIp("192.168.234.82")).thenReturn(logs_ip);
		List<Log> list = new ArrayList<Log>();
		Iterator<Log> iterator = this.controller.listLogsByIp("192.168.234.82").getBody().iterator();
		while (iterator.hasNext()) {
		    list.add(iterator.next());
		}
		assertEquals(list.size(), 2);
	}
	
	@Test
	public void test_list_logs_by_date() {
		when(this.service.listLogsByDate("2020-04-12 00:00:00.000", "2020-04-13 00:00:00.000")).thenReturn(logs);
		List<Log> list = new ArrayList<Log>();
		Iterator<Log> iterator = this.controller.listLogsByDate("2020-04-12 00:00:00.000", "2020-04-13 00:00:00.000").getBody().iterator();
		while (iterator.hasNext()) {
		    list.add(iterator.next());
		}
		assertEquals(list.size(), logs.size());
	}
	
	@Test
	public void test_list_logs_by_ip_and_date() {
		List<Log> logs_ip_date =  new ArrayList<Log>();
		logs_ip_date.add(logs.get(0));
		logs_ip_date.add(logs.get(1));
		when(this.service.listLogsByIpAndDate("192.168.234.82", "2020-04-12 00:00:00.000", "2020-04-13 00:00:00.000")).thenReturn(logs_ip_date);
		List<Log> list = new ArrayList<Log>();
		Iterator<Log> iterator = this.controller.listLogsByIpAndDate("192.168.234.82", "2020-04-12 00:00:00.000", "2020-04-13 00:00:00.000").getBody().iterator();
		while (iterator.hasNext()) {
		    list.add(iterator.next());
		}
		assertEquals(list.size(), 2);
	}
	
	@Test
	public void test_save_log() {
		when(this.service.saveLog(any(Log.class))).thenReturn(logs.get(0));
		assertEquals(this.controller.saveLog(dto).getBody().getDate(), logs.get(0).getDate());
		assertEquals(this.controller.saveLog(dto).getBody().getIp(), logs.get(0).getIp());
		assertEquals(this.controller.saveLog(dto).getBody().getRequest(), logs.get(0).getRequest());
		assertEquals(this.controller.saveLog(dto).getBody().getStatus(), logs.get(0).getStatus());
		assertEquals(this.controller.saveLog(dto).getBody().getUserAgent(), logs.get(0).getUserAgent());
	}


}
