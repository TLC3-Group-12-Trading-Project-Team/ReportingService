package com.example.reportingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ReportingService {

	public static void main(String[] args) {
		SpringApplication.run(ReportingService.class, args);
	}

	@GetMapping(path = "/")
	public String index(){
		return "This is the index for the reporting service";
	}
}
