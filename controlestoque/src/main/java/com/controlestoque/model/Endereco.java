package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Embeddable
public class Endereco  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "codigo_bloco")
	private Bloco bloco;
	
	@Transient
	private Rua rua;

	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}
	
	public String getNomeBlcoPelaRua() {
		if(this.bloco != null) {
			return this.bloco.getNome() + "/"+ this.bloco.getRua().getNome();
		}
		return null;
	}	

}
