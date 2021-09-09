package com.marcosramiro.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/java11")
public class NovidadesJava11 {

    @GetMapping("/var")
    public String usoDoVar(){
        var lista = List.of(1,2,3,4);
        lista.forEach((var e) -> System.out.println(e));

        return lista.toString();
    }

    @GetMapping("/httpclient/{path}/{id}")
    public ResponseEntity<String> httpClient(@PathVariable("path") String path, @PathVariable ("id") String id)
            throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(1))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://swapi.dev/api/" + path + "/" + id + "/"))
                .GET()
                .timeout(Duration.ofSeconds(2))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return ResponseEntity
                .status(response.statusCode())
                .body(response.body());
    }
}
