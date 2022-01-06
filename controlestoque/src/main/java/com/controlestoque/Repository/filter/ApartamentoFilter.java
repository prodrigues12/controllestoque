package com.controlestoque.Repository.filter;

import com.controlestoque.model.Bloco;

public class ApartamentoFilter {
	
	private Long codigo;
	
	private String nome;
	
	private Bloco bloco;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}
	
	

}
