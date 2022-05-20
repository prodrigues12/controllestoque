package com.controlestoque.Repository.filter;

import java.math.BigDecimal;

import com.controlestoque.model.Produto;



public class EnderecoFilter {
	
	private Long codigo;
	private String endereco;
	private Produto produto;
	private BigDecimal quantidade;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
	
	

}
