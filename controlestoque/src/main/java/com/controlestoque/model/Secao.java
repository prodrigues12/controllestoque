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
	
//	## Construtor
	
	public Secao() {
		
	}
	
public Secao(Long idSecao, String nomesecao, List<Produto> produtos) {
	super();
	this.idSecao = idSecao;
	this.nomesecao = nomesecao;
	this.produtos = produtos;
}


//	## Get's and Set's 
	
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
	
//	## hasCode and equals

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSecao == null) ? 0 : idSecao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Secao other = (Secao) obj;
		if (idSecao == null) {
			if (other.idSecao != null)
				return false;
		} else if (!idSecao.equals(other.idSecao))
			return false;
		return true;
	}


	
}
