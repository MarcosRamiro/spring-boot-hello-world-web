package com.marcosramiro.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcosramiro.spring.service.PIService;

@RequestMapping("/PI")
@RestController
public class PIController {
	
	@Autowired
	PIService piService;
	
	@GetMapping("/{numero}")
	public String calculoDoPI(@PathVariable("numero") String numero) {
		
		return String.format("%f", piService.pi(Integer.valueOf(numero)));
		
	}

}
