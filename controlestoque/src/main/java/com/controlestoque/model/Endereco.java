package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

//@Embeddable

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

//	@Transient
	private Rua rua;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_produto")
	private Produto produto;

	@Transient
	@ManyToOne
	@JoinColumn(name = "codigo_bloco")
	private Bloco bloco;

	@Transient
	@ManyToOne
	@JoinColumn(name = "codigo_apartamento")
	private Apartamento apartamento;
	
//	@ManyToOne
//	@JoinColumn(name = "codigo_sala")
//	private Sala sala;
	

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
			return this.apartamento.getBloco().getRua().getNome() + " - " + this.apartamento.getBloco().getNome()
					+ " - " + this.apartamento.getNome();
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
