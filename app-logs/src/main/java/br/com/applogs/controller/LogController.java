package br.com.applogs.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.applogs.model.Log;
import br.com.applogs.model.LogAggregationResult;
import br.com.applogs.model.converter.LogConverter;
import br.com.applogs.model.dto.LogDTO;
import br.com.applogs.service.LogService;

@RestController
@RequestMapping("/logs")
public class LogController {
	
	
	@Autowired
	private JobLauncher jobLauncher;
	 
	@Autowired
	private Job job;
	
	@Autowired
	private LogService logService;
		
	 
	@PostMapping(value="/batch_file", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> readLogsFromFile(@RequestParam("uploadFile") MultipartFile uploadFile) throws Exception {
 
		byte[] bytes = uploadFile.getBytes();
		String rootPath = System.getProperty("user.dir");
        Path path = Paths.get(rootPath + "/src/main/resources/" + uploadFile.getOriginalFilename());
        Files.write(path, bytes);
        System.out.println(path.toUri());
        
		//Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//logger.info(e.getMessage());
		}
		return new ResponseEntity<String>("Leitura e escrita de logs a partir de arquivo realizadas com sucesso!", HttpStatus.OK);
	}
	
	@GetMapping(value="/list", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Log>> listAllLogs() {
		return new ResponseEntity<Iterable<Log>>(logService.listAllLogs(), HttpStatus.OK);
	}
	
	@PostMapping(value="/save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveLog(@RequestBody LogDTO logDTO) {
		Log log = logService.saveLog(LogConverter.DTOToModel(logDTO));
		return new ResponseEntity<String>("Log de ID: " + log.getId() + " salvo com sucesso", HttpStatus.OK);
	}
	
	@GetMapping(value="/listByIp/{ip}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Log>> listLogsByIp(@PathVariable("ip") String ip) {
		return new ResponseEntity<Iterable<Log>>(logService.listLogsByIp(ip), HttpStatus.OK);
	}
	
	@GetMapping(value="/listByDate/{startTime}/{endTime}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Log>> listLogsByDate(@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		return new ResponseEntity<Iterable<Log>>(logService.listLogsByDate(startTime, endTime), HttpStatus.OK);
	}
	
	@GetMapping(value="/listByIpAndDate/{ip}/{startTime}/{endTime}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Log>> listLogsByIpAndDate(@PathVariable("ip") String ip, @PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		return new ResponseEntity<Iterable<Log>>(logService.listLogsByIpAndDate(ip, startTime, endTime), HttpStatus.OK);
	}
	
}
