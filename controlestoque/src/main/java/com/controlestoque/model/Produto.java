package com.controlestoque.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.controlestoque.Enums.UnidadeMedia;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Size(min = 7, max = 40, message = "O campo ''Nome'' deve contar de 7 à 40 caracteries")
	@NotBlank
	private String nome;

	private String descricao;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal qtdEstoque;

	// @NotNull(message = "Campo é obrigatória")
	@DecimalMin(value = "1.0", message = "Estoque deve ser no mínimo 1")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal qtdEstMin;

	@NotNull(message = "Campo 'Seção' é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_secao")
	private Secao secao;

	@JsonIgnore
	@Embedded
	private Agrupar agrupar;

	@NotNull(message = "O unidade de medida é obrigatório")
	@Enumerated(EnumType.STRING)
	private UnidadeMedia uniMedida;

	@JsonIgnore
	@Embedded
	private Endereco endereco;

	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(BigDecimal qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public BigDecimal getQtdEstMin() {
		return qtdEstMin;
	}

	public void setQtdEstMin(BigDecimal qtdEstMin) {
		this.qtdEstMin = qtdEstMin;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Agrupar getAgrupar() {
		return agrupar;
	}

	public void setAgrupar(Agrupar agrupar) {
		this.agrupar = agrupar;
	}

	public UnidadeMedia getUniMedida() {
		return uniMedida;
	}

	public void setUniMedida(UnidadeMedia uniMedida) {
		this.uniMedida = uniMedida;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public boolean isNovo() {
		return codigo == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
