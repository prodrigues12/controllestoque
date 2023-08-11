package com.controlestoque.Enums;

public enum TipoAjusteEstoque {
	
	AVARIA("AVARIA"),
	ENTRADA("ENTRADA DE PRODUTO"),
	INVENTARIO("INVENTARIO"),
	MOVIMENTACAO_SEM_REGISTRO("MOVIMENTAÇÃO SEM REGISTRO");
	
	private String descricao;
	
	private TipoAjusteEstoque(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	

}
