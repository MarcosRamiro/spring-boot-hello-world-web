package com.marcosramiro.spring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.marcosramiro.spring.service.RegionService;
import com.marcosramiro.spring.service.impl.BeanFactoryDynamicAutowireService;
import com.marcosramiro.spring.service.impl.GBRegionService;
import com.marcosramiro.spring.service.impl.USRegionService;


@SpringBootTest
public class TesteDynamicAutowiredControllerTest {
	
	@InjectMocks
	private TesteDynamicAutowiredController testeDynamicAutowiredController;
	
	@Spy
	private List<RegionService> listRegionService = new ArrayList<>();
		
	@Mock
	RegionService region;
	
	@Mock
	BeanFactoryDynamicAutowireService beanFactoryDynamicAutowireService;
	
	@Test
	public void deveRetornaUSADeOutraService() {
		
		when(beanFactoryDynamicAutowireService.getBean("US")).thenReturn(region);
		when(region.getISOCountryCode()).thenReturn("USA!");
		
		String sayHelloAgain = testeDynamicAutowiredController.sayHello("US");
		
		assertEquals("USA!", sayHelloAgain);
		
	}
	
	@Test
	public void deveRetornaUSA() {

		listRegionService.clear();
		listRegionService.add(region);

		when(region.isResponsibleFor(anyString())).thenReturn(true);
		when(region.getISOCountryCode()).thenReturn("USA");
		
		String sayHelloAgain = testeDynamicAutowiredController.sayHelloAgain("US");
		
		assertEquals("USA", sayHelloAgain);
		
	}
	
	
	@Test
	public void deveRetornaReinoUnido() {

		listRegionService.clear();
		listRegionService.add(region);
		
		when(region.isResponsibleFor(anyString())).thenReturn(true);
		when(region.getISOCountryCode()).thenReturn("Reino Unido");
		
		String sayHelloAgain = testeDynamicAutowiredController.sayHelloAgain("US");
		
		assertEquals("Reino Unido", sayHelloAgain);
		
	}
	
	@Test
	public void deveRetornaNadaEncontrado() {

		listRegionService.clear();
		
		String sayHelloAgain = testeDynamicAutowiredController.sayHelloAgain("US");
		
		assertEquals("nada encontrado", sayHelloAgain);
		
	}
}
