package br.com.applogs.model.converter;


import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.applogs.model.Log;
import br.com.applogs.model.dto.LogDTO;


@RunWith(MockitoJUnitRunner.class)
public class LogConverterTests {

	
	private LogDTO dto;
	
	
	private Log log;
	
	
	@Before
	public void setUp() {
		dto = new LogDTO("2020-04-12 00:00:00.000", "192.168.234.82", "GET / HTTP/1.1", 200, "Google Chrome");
		log = new Log(UUID.randomUUID().toString(), LocalDateTime.parse("2020-04-12 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), "192.168.234.82", "GET / HTTP/1.1", 200, "Google Chrome");
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void test_dto_to_model() {
		Log logReturn = LogConverter.DTOToModel(dto);
		assertEquals(logReturn.getDate(), log.getDate());
		assertEquals(logReturn.getIp(), log.getIp());
		assertEquals(logReturn.getRequest(), log.getRequest());
		assertEquals(logReturn.getStatus(), log.getStatus());
		assertEquals(logReturn.getUserAgent(), log.getUserAgent());
	}
	
	@Test
	public void test_model_to_dto() {
		LogDTO logDTOReturn = LogConverter.modelToDTO(log);
		assertEquals(logDTOReturn.getDate(), log.getDate().toString());
		assertEquals(logDTOReturn.getIp(), log.getIp());
		assertEquals(logDTOReturn.getRequest(), log.getRequest());
		assertEquals(logDTOReturn.getStatus(), log.getStatus());
		assertEquals(logDTOReturn.getUserAgent(), log.getUserAgent());
	}
	

}
