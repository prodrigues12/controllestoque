package com.controlestoque.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rua implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRua;

	private String nomeRua;

	@OneToMany
	private List<Produto> produtos;

//	## Construtor

	public Rua() {

	}

	public Rua(Long idRua, String nomeRua, List<Produto> produtos) {
		super();
		this.idRua = idRua;
		this.nomeRua = nomeRua;
		this.produtos = produtos;
	}

//	## GET's and SET's

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

//	## hasCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRua == null) ? 0 : idRua.hashCode());
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
		Rua other = (Rua) obj;
		if (idRua == null) {
			if (other.idRua != null)
				return false;
		} else if (!idRua.equals(other.idRua))
			return false;
		return true;
	}

}
