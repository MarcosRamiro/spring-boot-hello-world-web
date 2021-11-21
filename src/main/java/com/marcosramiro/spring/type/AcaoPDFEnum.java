package com.marcosramiro.spring.type;

public enum AcaoPDFEnum {
	
	IMPRIMIR("inline"),
	DOWNLOAD("attachment");
	
	private String disposition;
	
	private AcaoPDFEnum(String disposition) {
		this.disposition = disposition;
	}
	
	public String getDisposition() {
		return this.disposition;
	}
}
