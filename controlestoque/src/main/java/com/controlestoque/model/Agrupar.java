package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Embeddable
public class Agrupar implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "codigo_subgrupo")
	private Subgrupo subgrupo;

	@Transient
	private Grupo grupo;
		

	public Subgrupo getSubgrupo() {
		return subgrupo;
	}


	public void setSubgrupo(Subgrupo subgrupo) {
		this.subgrupo = subgrupo;
	}


	public Grupo getGrupo() {
		return grupo;
	}


	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}


	

}
