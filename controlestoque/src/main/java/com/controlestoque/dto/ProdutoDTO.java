package com.controlestoque.dto;

import java.math.BigDecimal;

import com.controlestoque.Enums.UnidadeMedia;

public class ProdutoDTO {

	private Long codigo;
	private String nome;
	private String secao;
	private BigDecimal estoque;
	private String uniMedida;

	public ProdutoDTO(Long codigo, String nome, String secao, BigDecimal estoque, UnidadeMedia uniMedida) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.secao = secao;
		this.estoque = estoque;
		this.uniMedida = uniMedida.getDescricao();

	}

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

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public BigDecimal getEstoque() {
		return estoque;
	}

	public void setEstoque(BigDecimal estoque) {
		this.estoque = estoque;
	}

	public String getUniMedida() {
		return uniMedida;
	}

	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
	}

}
