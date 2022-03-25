package com.controlestoque.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull(message = "Campo Produto é obrigatorio")
	@ManyToOne
	@JoinColumn(name = "codigo_produto")
	private Produto produto;

	@NotNull(message = "Campo Rua é obrigatorio")
	@ManyToOne
	@JoinColumn(name = "codigo_rua")
	private Rua rua;

	@NotNull(message = "Campo Rua é obrigatorio")
	@ManyToOne
	@JoinColumn(name = "codigo_bloco")
	private Bloco bloco;

	@ManyToOne
	@JoinColumn(name = "codigo_apartamento")
	private Apartamento apartamento;

	@ManyToOne
	@JoinColumn(name = "codigo_sala")
	private Sala sala;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(codigo, other.codigo);
	}

	public String getEndereco() {
		if (this.sala != null) {
			return this.rua.getNome() + " - " + bloco.getNome() + " - " + this.apartamento.getNome() + " - "
					+ this.sala.getNome();
		}

		if (this.apartamento != null) {
			return this.rua.getNome() + " - " + bloco.getNome() + " - " + this.apartamento.getNome();
		}

		return this.rua.getNome() + " - " + bloco.getNome();

	}
}
