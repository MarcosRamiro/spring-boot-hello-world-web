package com.marcosramiro.spring;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {

        String parametro = args != null ? Arrays.stream(args).reduce("",(s, s2) -> s + " " + s2) : "esta nulo";

        LOGGER.info(parametro);

        SpringApplication.run(Application.class, args);
    }

}
