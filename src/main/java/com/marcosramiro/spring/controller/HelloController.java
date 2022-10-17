package com.marcosramiro.spring.controller;

import java.util.function.Predicate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello() {

    	int number = 2;
        print(5, i -> i > number);
        return "hello";
    
    }

	private void print(int valor, Predicate<Integer> predicate) {

        if (predicate.test(Integer.valueOf(valor)))
            System.out.println("Passou");

    }
    
}

