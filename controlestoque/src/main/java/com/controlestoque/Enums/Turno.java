package com.controlestoque.Enums;

public enum Turno {

	PRIMEIRO("1º Turno"),
	SEGUNDO("2º Turno"),
	TERCEIRO("3º Turno"),
	COMERCIAL("Comercial");
	
	
	private String descricao;
	
	private Turno(String descricao) {
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
