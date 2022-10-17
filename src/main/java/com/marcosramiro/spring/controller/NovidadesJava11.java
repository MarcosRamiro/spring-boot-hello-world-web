package com.marcosramiro.spring.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcosramiro.spring.filter.CheckClientRequestFilter;

@RestController
@RequestMapping("/java11")
public class NovidadesJava11 {

	private static Logger LOGGER = LoggerFactory.getLogger(NovidadesJava11.class);

	@GetMapping(value = "/var/{de}/{para}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String usoDoVar(@PathVariable("de") String de, @PathVariable("para") String para) {

		Instant inicio = Instant.now();
		LocalTime time = LocalTime.now();

		LOGGER.info("LocalTime.now() --> {}", time);

		var lista = IntStream.range(Integer.valueOf(de), Integer.valueOf(para)).filter(e -> e % 11 == 0)
				.collect(() -> new HashSet<>(), (l, i) -> l.add(i), (l1, l2) -> l1.addAll(l2));

		Duration duratin = Duration.between(inicio, Instant.now());

		return String.valueOf(
				"{ \"tempo\":\"" + String.valueOf(duratin.toMillis()) + "\" , \"itens\":\"" + lista.size() + "\" }");

	}

	@GetMapping("/httpclient/{path}/{id}")
	public ResponseEntity<String> httpClient(@PathVariable("path") String path, @PathVariable("id") String id)
			throws IOException, InterruptedException {

		HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(3))
				.followRedirects(HttpClient.Redirect.NORMAL).build();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://swapi.dev/api/" + path + "/" + id + "/"))
				.GET().timeout(Duration.ofSeconds(2)).build();

		var response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return ResponseEntity.status(response.statusCode()).body(response.body());
	}

	@GetMapping("/jersey/{path}/{id}")
	public ResponseEntity<String> jersey(@PathVariable("path") String path, @PathVariable("id") String id) {

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(CheckClientRequestFilter.class);
		Client client = ClientBuilder.newClient(clientConfig);

		// wiremock
		// WebTarget target = client.target("http://localhost:9999/api");
		WebTarget target = client.target("https://swapi.dev/api");

		Response response = target.path(path).path(id).request().get();

		return ResponseEntity
				.status(response.getStatus())
				.contentType(MediaType.valueOf(response.getMediaType().toString()))
				.body(response.readEntity(String.class));

	}

	@GetMapping("/stream")
	public ResponseEntity<String> stream() {

		List<String> lista = List.of("Olá", "Mundo", "Legal", "Marcos Ramiro");

		final String s1 = lista.stream()
							.filter(s -> s.length() > 3)
							.findFirst()
							.orElse("nada");

		return ResponseEntity.ok().body(s1);

	}

}
