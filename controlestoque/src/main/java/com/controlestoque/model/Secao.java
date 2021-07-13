package com.controlestoque.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Secao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSecao;
	
	private String nomesecao;
	
	@OneToMany
	private List<Produto> produtos;
	
	
//	##### Get's and Set's #####
	
	public Long getIdSecao() {
		return idSecao;
	}

	public void setIdSecao(Long idSecao) {
		this.idSecao = idSecao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	

	public String getNomesecao() {
		return nomesecao;
	}

	public void setNomesecao(String nomesecao) {
		this.nomesecao = nomesecao;
	}


	
}
