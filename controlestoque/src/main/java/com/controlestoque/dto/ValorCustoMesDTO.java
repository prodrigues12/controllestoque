package com.controlestoque.dto;

import java.math.BigDecimal;

public class ValorCustoMesDTO {
	
	private String nome;
	private Integer quantidade;
	private BigDecimal valor;
	
	public ValorCustoMesDTO(String nome, int quantidade, BigDecimal valor) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getQuantidade() {
		return quantidade;
	}

	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	
	public BigDecimal getValor() {
		return valor;
	}

	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
	

}
