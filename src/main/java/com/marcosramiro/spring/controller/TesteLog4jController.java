package com.marcosramiro.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class TesteLog4jController {
	
	private Logger LOGGER = LogManager.getLogger(TesteLog4jController.class);

	@GetMapping
	public ResponseEntity<String> logar(@RequestParam("message") String message){
		
		
		LOGGER.info("isso é apenas um log: {}", message);
		
		return ResponseEntity.ok().body(message);
	}
	
}
