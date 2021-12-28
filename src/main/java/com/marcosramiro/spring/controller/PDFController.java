package com.marcosramiro.spring.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcosramiro.spring.form.PDFForm;
import com.marcosramiro.spring.type.AcaoPDFEnum;

@RestController
@RequestMapping("/pdf")
public class PDFController {

	@GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<ByteArrayResource> gerarPdfGet(@RequestParam("acao") String requestAcao) throws IOException {
		
		return tratarRequisicao(definirTipoDeAcao(requestAcao));
		
	}

	@PostMapping(produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<ByteArrayResource> gerarPdf(@RequestBody PDFForm requestAcao) throws IOException {
		
		return tratarRequisicao(definirTipoDeAcao(requestAcao.getAcao()));
		
	}

	private ResponseEntity<ByteArrayResource> tratarRequisicao(AcaoPDFEnum acao) throws IOException {

		ByteArrayResource arquivo = obterArquivo();

		return prepararRetorno(arquivo, acao.getDisposition());

	}

	private ByteArrayResource obterArquivo() throws IOException {

		InputStream recurso = obterRecurso();
		
		ByteArrayResource resource = new ByteArrayResource(recurso.readAllBytes());
		
		return resource;

	}

	private InputStream obterRecurso() throws IOException {
			
		String base64 = IOUtils.resourceToString("/file/base64_pdf.txt", StandardCharsets.UTF_8);
		
		PDDocument doc = PDDocument.load(java.util.Base64.getMimeDecoder().decode(base64));
		
		PDDocumentInformation info = new PDDocumentInformation();
		info.setAuthor("Autor:: Marcos Ramiro dos Santos");
		info.setTitle("Esse é o titulo legal!!");
		info.setSubject("Esse é o assunto bom....");
		info.setCreator("Criador:: Ramiro");
		
		doc.setDocumentInformation(info);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		doc.save(byteArrayOutputStream);
		doc.close();
		
		InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		return inputStream;
	}

	private AcaoPDFEnum definirTipoDeAcao(String acao) {

		return Enum.valueOf(AcaoPDFEnum.class, acao.toUpperCase());

	}

	private ResponseEntity<ByteArrayResource> prepararRetorno(ByteArrayResource arquivo, String disposition) {

		return ResponseEntity.ok()
				// Content-Disposition
				.header(HttpHeaders.CONTENT_DISPOSITION, disposition + ";filename=teste.pdf")
				// Content-Type
				.contentType(MediaType.APPLICATION_PDF)
				// Content-Length
				.contentLength(arquivo.contentLength()) //
				.body(arquivo);
	}
	
}
