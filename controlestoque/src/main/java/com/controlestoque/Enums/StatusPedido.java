package com.controlestoque.Enums;

public enum StatusPedido {

	NOVO("Novo"), 
	PENDENTE("Pendente"),	
	SEPARACAO("Em Separação"),
	FINALIZADO("Finalizado"), 
	CANCELADO("Cancelado");

	private String descricao;

	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
