package com.marcosramiro.spring.controller;

import com.marcosramiro.spring.form.PDFForm;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


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
    	
        if(!acao.equalsIgnoreCase("imprimir") && !acao.equalsIgnoreCase("download"))
            return ResponseEntity.badRequest().build();
        
    	String base64 = IOUtils.resourceToString("/teste.txt", StandardCharsets.UTF_8);
        
    	byte[] decoded = java.util.Base64.getMimeDecoder().decode(base64);
        ByteArrayResource resource = new ByteArrayResource(decoded);

        String downloadOuImprimir = acao.equalsIgnoreCase("imprimir") ? "inline" : "attachment";

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, downloadOuImprimir + ";filename=teste.pdf")
                // Content-Type
                .contentType(MediaType.APPLICATION_PDF)
                // Contet-Length
                .contentLength(resource.contentLength()) //
                .body(resource);

    }

}
