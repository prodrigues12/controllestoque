package com.controlestoque.Enums;

public enum TipoColaborador {
	
	TERCERIZADO("Tercerizado"), MAGALU("Magalu") , FILIAL("Filial");

	private String descricao;

	private TipoColaborador(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
