package com.marcosramiro.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        String parametro = args != null ? Arrays.stream(args).reduce("",(s, s2) -> s + " " + s2) : "esta nulo";

        System.out.println(parametro);

        SpringApplication.run(Application.class, args);
    }

}
