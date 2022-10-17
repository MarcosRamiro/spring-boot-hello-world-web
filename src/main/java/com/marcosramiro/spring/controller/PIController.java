package com.marcosramiro.spring.controller;

import javax.servlet.http.HttpServletRequest;

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
	public String calculoDoPI(@PathVariable("numero") String numero, HttpServletRequest request) {
		
		System.out.println(request.getRequestURL().toString());
		return String.format("%f - %s", piService.pi(Integer.valueOf(numero)), request.getLocalName());
		
	}

}
