package com.controlestoque.Enums;

public enum UnidadeMedia {

	UN("UN - Unidade"),
	CX("CX - Caixa"),
	L("L - Litro"),
	KG("KG - Quilograma"),
	PCT("PCT - Pacote ");
	
	private String descricao;
	
	private UnidadeMedia(String descricao) {
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
