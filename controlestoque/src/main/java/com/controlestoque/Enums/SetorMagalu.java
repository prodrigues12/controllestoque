package com.controlestoque.Enums;

public enum SetorMagalu {
	
	ATENDIMENTO("ATENDIMENTO"),
	CTE("CTE"),
	DQS("DQS"),
	EXPEDICAO("EXPEDIÇÃO"),
	GESTAO_PESSOA("GESTÃO DE PESSOA"),
	GERENCIA("GERÊNCIA"),
	INTELIGENCIA("INTELIGÊNCIA"),
	LIMPEZA("LIMPEZA"),
	RECEBIMENTO("RECEBIMENTO"),
	SESMIT("SESMIT"),
	SUPLIMENTOS("SUPLIMENTOS"),
	TERCERIZADOS("TERCERIZADOS"),
	TI("T.I."),
	TRANSPORTE("TRANSPORTE");
	
private String descricao;
	
	private SetorMagalu(String descricao) {
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	

}
