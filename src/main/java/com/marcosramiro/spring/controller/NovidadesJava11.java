package com.marcosramiro.spring.controller;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcosramiro.spring.filter.CheckClientRequestFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
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

    @GetMapping(value="/var", produces = MediaType.APPLICATION_JSON_VALUE)
    public String usoDoVar(){
        var lista = List.of(1,2,3,4);
        lista
        .stream()
        .filter(e -> e % 2 == 0)
        .forEach((var e) -> System.out.println(e));

        return lista.toString();
    }

    @GetMapping("/httpclient/{path}/{id}")
    public ResponseEntity<String> httpClient(@PathVariable("path") String path, @PathVariable ("id") String id)
            throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
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

    @GetMapping("/jersey/{path}/{id}")
    public ResponseEntity<String> jersey(@PathVariable("path") String path, @PathVariable ("id") String id){

    	ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(CheckClientRequestFilter.class);
        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget target = client.target("https://swapi.dev/api");

        Response response = target
                .path(path)
                .path(id)
                .request()
                .get();

        return ResponseEntity
                .status(response.getStatus())
                .contentType(MediaType.valueOf(response.getMediaType().toString()))
                .body(response.readEntity(String.class));

    }


}
