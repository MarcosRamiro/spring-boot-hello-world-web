package com.marcosramiro.spring.type;

public enum AcaoPDFEnum {
	
	IMPRIMIR("imprimir"){
		@Override
		public String getDisposition() {
			return "inline";
		}
	},
	DOWNLOAD("download") {
		@Override
		public String getDisposition() {
			return "attachment";
		}
	};
	
	private String acao;
	
	private AcaoPDFEnum(String acao) {
		this.acao = acao;
	}
	
	public String getAcao() {
		return this.acao;
	}
	
	public abstract String getDisposition();

}
