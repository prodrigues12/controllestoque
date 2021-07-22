package com.controlestoque.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rua {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRua;
	
	private String nomeRua;
	
	@OneToMany
	private List<Produto> produtos;
	
//	### GET's and SET's ###

	public Long getIdRua() {
		return idRua;
	}

	public void setIdRua(Long idRua) {
		this.idRua = idRua;
	}

	public String getNomeRua() {
		return nomeRua;
	}

	public void setNomeRua(String nomeRua) {
		this.nomeRua = nomeRua;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	

	
	

}
