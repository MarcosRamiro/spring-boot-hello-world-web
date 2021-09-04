package com.marcosramiro.spring.controller;

import org.apache.commons.codec.binary.Base64;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/pdf")
public class PDFController {

    @ResponseBody
    @GetMapping(value = "/download",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> download() throws IOException {
        return preparaRetorno("download");
    }

    @ResponseBody
    @GetMapping(value = "/imprimir",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> imprimir() throws IOException {
        return preparaRetorno("imprimir");
    }

    private ResponseEntity<ByteArrayResource> preparaRetorno(String acao) throws IOException {

        Path path = Paths.get("D:\\teste.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        ByteArrayResource resource = new ByteArrayResource(fromBase64(lines.stream().findFirst().get()));

        String downloadImprimir = acao.equalsIgnoreCase("imprimir") ? "inline" : "attachment";

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, downloadImprimir + ";filename=marcos.pdf")
                // Content-Type
                .contentType(MediaType.APPLICATION_PDF)
                // Contet-Length
                .contentLength(resource.contentLength()) //
                .body(resource);

    }

    private byte[] fromBase64(String base64) {

        Base64 base64Decoder = new Base64();
        return base64Decoder.decode(base64);

    }
}
