package com.marcosramiro.spring.config;

import java.lang.reflect.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcosramiro.spring.handler.LogProxyHandler;
import com.marcosramiro.spring.service.PIService;
import com.marcosramiro.spring.service.impl.PIServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	public PIService getPIService() {
		
		PIService proxyInstance = (PIService) Proxy.newProxyInstance(
				PIService.class.getClassLoader(), 
				  new Class[] { PIService.class }, 
				  new LogProxyHandler(new PIServiceImpl()));
		
		return proxyInstance;
	}
	
	
}
