package com.marcosramiro.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcosramiro.spring.service.impl.BeanFactoryDynamicAutowireService;

@RestController
@RequestMapping("/autowired")
public class TesteDynamicAutowiredController {

	@Autowired
	BeanFactoryDynamicAutowireService beanFactoryDynamicAutowireService;
	
	@GetMapping("/{isoCountryCode}")
	public String sayHello(@PathVariable("isoCountryCode") String isoCountryCode) {
		
		return beanFactoryDynamicAutowireService.getBean(isoCountryCode).getISOCountryCode();
		
	}
}
