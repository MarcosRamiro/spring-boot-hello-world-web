package com.marcosramiro.spring.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcosramiro.spring.Application;
import com.marcosramiro.spring.service.RegionService;
import com.marcosramiro.spring.service.impl.BeanFactoryDynamicAutowireService;

@RestController
@RequestMapping("/autowired")
public class TesteDynamicAutowiredController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(TesteDynamicAutowiredController.class);
	
	public TesteDynamicAutowiredController() {
		LOGGER.info("Construtor TesteDynamicAutowiredController");
	}

	@Autowired
	BeanFactoryDynamicAutowireService beanFactoryDynamicAutowireService;
	
	@Autowired
	List<RegionService> listRegionService;
	
	@GetMapping("/{isoCountryCode}")
	public String sayHello(@PathVariable("isoCountryCode") String isoCountryCode) {
		
		return beanFactoryDynamicAutowireService.getBean(isoCountryCode).getISOCountryCode();
		
	}
	
	@GetMapping("/new/{isoCountryCode}")
	public String sayHelloAgain(@PathVariable("isoCountryCode") String isoCountryCode) {
		
		Optional<RegionService> findFirst = listRegionService.stream()
		.filter(r -> r.isResponsibleFor(isoCountryCode))
		.findFirst()
		;
		
		if (findFirst.isEmpty())
			return "nada encontrado";
		
		return findFirst.get().getISOCountryCode();
		
	}
}
