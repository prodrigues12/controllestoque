package com.controlestoque.Enums;

public enum StatusPedido {

	NOVO("Novo"), 
	FINALIZADO("Finalizado"), 
	CANCELADO("Cancelado"),
	ESPERA("Espera");

	private String descricao;

	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
