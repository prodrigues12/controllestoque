package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;
	

	private String nome;
	
	private String descricao;
	
	private int qtdEstoque;
	
	@ManyToOne
	private Secao secoes;
	
	@ManyToOne
	private Grupo grupos;
	
	@ManyToOne
	private Rua ruas;

//	###*** Get's and Set's ***###


	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Secao getSecoes() {
		return secoes;
	}

	public void setSecoes(Secao secoes) {
		this.secoes = secoes;
	}

	public Grupo getGrupos() {
		return grupos;
	}

	public void setGrupos(Grupo grupos) {
		this.grupos = grupos;
	}
	
	public Rua getRuas() {
		return ruas;
	}

	public void setRuas(Rua ruas) {
		this.ruas = ruas;
	}
	

}
	