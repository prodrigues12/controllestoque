package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private Rua rua;

	@Transient
	@ManyToOne
	@JoinColumn(name = "codigo_bloco")
	private Bloco bloco;

	
	@ManyToOne
	@JoinColumn(name = "codigo_apartamento")
	private Apartamento apartamento;


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

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}


	public String getEnderecoProduto() {

		

		if (this.apartamento != null) {
			return this.rua.getNome() + " . " + this.bloco.getNome() + " . " + this.apartamento.getNome();
		}

		if (this.bloco != null) {
			return this.rua.getNome() + " . " + this.bloco.getNome();
		}
//		if (this.rua != null) {
			return this.rua.getNome();
//		}
//		return null;

	}
}
