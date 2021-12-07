package com.controlestoque.Repository.filter;

import java.math.BigDecimal;

import com.controlestoque.Enums.UnidadeMedia;
import com.controlestoque.model.Secao;

public class ProdutoFilter {
	
	private Long codigo;
	private String nome;
	private UnidadeMedia uniMedida;
	private Secao secao;
	private BigDecimal qtdEstoque;
	
	
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
	public UnidadeMedia getUniMedida() {
		return uniMedida;
	}
	public void setUniMedida(UnidadeMedia uniMedida) {
		this.uniMedida = uniMedida;
	}
	public Secao getSecao() {
		return secao;
	}
	public void setSecao(Secao secao) {
		this.secao = secao;
	}
	public BigDecimal getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(BigDecimal qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	
	
	

}
