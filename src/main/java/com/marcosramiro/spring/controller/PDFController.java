package com.marcosramiro.spring.controller;

import com.marcosramiro.spring.form.PDFForm;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/pdf")
public class PDFController {

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> gerarPdfGet(@RequestParam("acao") String acao) throws IOException {
        return preparaRetorno(acao);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> gerarPdf(@RequestBody PDFForm form) throws IOException {
        return preparaRetorno(form.getAcao());
    }

    private ResponseEntity<ByteArrayResource> preparaRetorno(String acao) throws IOException {

        Path path = Paths.get("D:\\teste.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String base64 = lines.stream().findFirst().get();

        byte[] decoded = java.util.Base64.getMimeDecoder().decode(base64);
        ByteArrayResource resource = new ByteArrayResource(decoded);

        if(!acao.equalsIgnoreCase("imprimir") && !acao.equalsIgnoreCase("download"))
            return ResponseEntity.badRequest().build();

        String downloadImprimir = acao.equalsIgnoreCase("imprimir") ? "inline" : "attachment";

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, downloadImprimir + ";filename=teste.pdf")
                // Content-Type
                .contentType(MediaType.APPLICATION_PDF)
                // Contet-Length
                .contentLength(resource.contentLength()) //
                .body(resource);

    }

}
