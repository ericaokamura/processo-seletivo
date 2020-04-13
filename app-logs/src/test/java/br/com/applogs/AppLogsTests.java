package br.com.applogs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.text.DateFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.applogs.controller.LogController;
import br.com.applogs.service.LogService;

@RunWith(JUnit4.class)
public class AppLogsTests {
	
	private WebDriver driver;
	
	private final static String baseUrl = "http://localhost:4200/";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseUrl + "log-arquivo");
		
		WebElement inputElement = driver.findElement(ByTagName.tagName("input"));
		inputElement.click();
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement buttonElement = driver.findElement(ByTagName.tagName("button"));
		buttonElement.click();
		
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test_logs_by_ip() {
		driver.get(baseUrl + "filtro-log");
		
		WebElement ipElement = driver.findElement(By.id("mat-input-0"));
		ipElement.sendKeys("192.168.234.82");
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		WebElement buttonElement = driver.findElement(ByTagName.tagName("button"));
		
		buttonElement.click();
		
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement table = driver.findElement(ByTagName.tagName("table"));
		List<WebElement> rows = table.findElements(ByTagName.tagName("tr"));
		for(int i=1; i<rows.size(); i++){
		  String ip = rows.get(i).findElement(ByClassName.className("mat-column-ip")).getText();
		  assertEquals(ip, "192.168.234.82");
		}	
		
	}
	
	@Test
	public void test_logs_by_date() {
		driver.get(baseUrl + "filtro-log");
		
		WebElement startTimeElement = driver.findElement(By.id("mat-input-1"));
		startTimeElement.sendKeys("2019-01-01 00:01:00.000");
		
		WebElement endTimeElement = driver.findElement(By.id("mat-input-2"));
		endTimeElement.sendKeys("2019-01-01 00:02:00.000");
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement buttonElement = driver.findElement(ByTagName.tagName("button"));
		
		buttonElement.click();
		
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDateTime startDate = LocalDateTime.parse("2019-01-01T00:01:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
		LocalDateTime endDate = LocalDateTime.parse("2019-01-01T00:02:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));

		WebElement table = driver.findElement(ByTagName.tagName("table"));
		List<WebElement> rows = table.findElements(ByTagName.tagName("tr"));
		for(int i=1; i<rows.size(); i++){
		  String date = rows.get(i).findElement(ByClassName.className("mat-column-date")).getText();
		  while(date.length() < 23) {
			  date = date + "0";
		  }
		  LocalDateTime data = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
		  
		  assertThat(data.isAfter(startDate));
		  assertThat(data.isBefore(endDate));
		}
	}
	
	@Test
	public void test_logs_by_ip_and_date() {
		driver.get(baseUrl + "filtro-log");
		
		WebElement ipElement = driver.findElement(By.id("mat-input-0"));
		ipElement.sendKeys("192.168.234.82");
		
		WebElement startTimeElement = driver.findElement(By.id("mat-input-1"));
		startTimeElement.sendKeys("2019-01-01 00:01:00.000");
		
		WebElement endTimeElement = driver.findElement(By.id("mat-input-2"));
		endTimeElement.sendKeys("2019-01-01 00:02:00.000");
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement buttonElement = driver.findElement(ByTagName.tagName("button"));
		
		buttonElement.click();
		
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LocalDateTime startDate = LocalDateTime.parse("2019-01-01T00:01:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
		LocalDateTime endDate = LocalDateTime.parse("2019-01-01T00:02:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));

		WebElement table = driver.findElement(ByTagName.tagName("table"));
		List<WebElement> rows = table.findElements(ByTagName.tagName("tr"));
		for(int i=1; i<rows.size(); i++){
		  String ip = rows.get(i).findElement(ByClassName.className("mat-column-ip")).getText();
		  String date = rows.get(i).findElement(ByClassName.className("mat-column-date")).getText();
		  while(date.length() < 23) {
			  date = date + "0";
		  }
		  LocalDateTime data = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
		  
		  assertEquals(ip, "192.168.234.82");
		  assertThat(data.isAfter(startDate));
		  assertThat(data.isBefore(endDate));
		}
	}

}
