package com.marcosramiro.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PIServiceImplTest {

	
	@InjectMocks
	PIServiceImpl piServiceImpl;
	
	@Test
	public void deveCalcularPI() {

		Assertions.assertEquals( String.format("%.6f", Math.PI), String.format("%.6f",  piServiceImpl.pi(10)));
		
		
	}
	
	
}
