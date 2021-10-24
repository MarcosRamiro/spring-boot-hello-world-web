package com.marcosramiro.spring.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.marcosramiro.spring.service.PIService;

@SpringBootTest
@AutoConfigureMockMvc
public class PIControllerTest {

	@InjectMocks
	PIController piController;
	
	@MockBean
	PIService piService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void calcularPI() throws Exception {
		
		when(piService.pi(Mockito.anyInt())).thenReturn(Math.PI);
		
		mockMvc.perform( get("/PI/1")  )
		.andExpect(status().isOk())
		.andExpect(content().string(String.format("%f", Math.PI) ));
		
	}

}
