package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Embeddable
public class Agrupar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "codigo_subGrupo")
	private SubGrupo subGrupo;

	@Transient
	private Grupo grupo;

	public SubGrupo getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getNomeSubgrupoPelaGrupo() {
		if (this.subGrupo != null) {
			return this.subGrupo.getNome() + "/" + this.subGrupo.getGrupo().getNome();
		}
		return null;
	}

}
