package com.controlestoque.Enums;

public enum StatusPedido {

	NOVO("Novo"), 
	SEPARACAO("Em Separação"),
	PENDENTE("Pendente"),
	CANCELADO("Cancelado"),
	FINALIZADO("Finalizado"); 
	
	

	private String descricao;

	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
