package com.controlestoque.Repository.filter;

import com.controlestoque.model.Rua;

public class BlocoFilter {
	
	private Long codigo;
	private String nome;
	private Rua rua;
	
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
	public Rua getRua() {
		return rua;
	}
	public void setRua(Rua rua) {
		this.rua = rua;
	}
	
	

}
