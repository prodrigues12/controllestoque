package com.controlestoque.Enums;

public enum Turno {

	PRIMEIRO("1º Tuno"),
	SEGUNDO("2º Tuno"),
	TERCEIRO("3º Tuno"),
	COMERCIAL("Comercial");
	
	
	private String descricao;
	
	private Turno(String descricao) {
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
