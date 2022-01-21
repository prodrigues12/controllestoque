package com.controlestoque.Repository.filter;

import com.controlestoque.model.Apartamento;
import com.controlestoque.model.Bloco;
import com.controlestoque.model.Produto;
import com.controlestoque.model.Rua;
import com.controlestoque.model.Sala;

public class EnderecoFilter {
	
	private Long codigo;
	private Produto produto;
	private Rua rua;
	private Bloco bloco;
	private Apartamento apartamento;
	private Sala sala;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Rua getRua() {
		return rua;
	}
	public void setRua(Rua rua) {
		this.rua = rua;
	}
	public Bloco getBloco() {
		return bloco;
	}
	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}
	public Apartamento getApartamento() {
		return apartamento;
	}
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	

}
