package com.marcosramiro.spring.type;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AcaoPDFEnumTest {
	
	@Test
	public void deveGerarAttachment() {
		
		AcaoPDFEnum acao = AcaoPDFEnum.DOWNLOAD;
		
		assertEquals("attachment", acao.getDisposition());
	}
	
	@Test
	public void deveGerarInline() {
		
		AcaoPDFEnum acao = AcaoPDFEnum.IMPRIMIR;
		
		assertEquals("inline", acao.getDisposition());
	}
	
	@Test
	public void deveRetornarONomeDoEnum() {
		
		AcaoPDFEnum imprimir = AcaoPDFEnum.IMPRIMIR;
		assertEquals("IMPRIMIR", imprimir.name());
		
		AcaoPDFEnum download = AcaoPDFEnum.DOWNLOAD;
		assertEquals("DOWNLOAD", download.name());
	}

}
