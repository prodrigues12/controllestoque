package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;
	
	@Size(min = 7, max = 40, message = "Nome do PRODUTO deve contar no minimo 7 caracteres no max 40")
	@NotEmpty
	@NotNull
	private String nome;
	
	private String descricao;
	
	private int qtdEstoque;
	
//	@Size(min = 0, message = "VALOR ESTOQUE MINIMO não pode ser menor que 0.")
	private int qtdEstMin;

	@ManyToOne
	private Secao secoes;
	
	@ManyToOne
	private Grupo grupos;
	
	@ManyToOne
	private Rua ruas;
	
	
//	## Construtor
	
	public Produto() {
		
	}

	
	
public Produto(Long idProduto,
		@Size(min = 7, max = 40, message = "Nome do PRODUTO deve contar no minimo 7 caracteres") @NotEmpty @NotNull String nome,
		String descricao, int qtdEstoque, int qtdEstMin, Secao secoes, Grupo grupos, Rua ruas) {
	super();
	this.idProduto = idProduto;
	this.nome = nome;
	this.descricao = descricao;
	this.qtdEstoque = qtdEstoque;
	this.qtdEstMin = qtdEstMin;
	this.secoes = secoes;
	this.grupos = grupos;
	this.ruas = ruas;
}


//	## Get's and Set's


	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
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

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	
	public int getQtdEstMin() {
		return qtdEstMin;
	}

	public void setQtdEstMin(int qtdEstMin) {
		this.qtdEstMin = qtdEstMin;
	}

	public Secao getSecoes() {
		return secoes;
	}

	public void setSecoes(Secao secoes) {
		this.secoes = secoes;
	}

	public Grupo getGrupos() {
		return grupos;
	}

	public void setGrupos(Grupo grupos) {
		this.grupos = grupos;
	}
	
	public Rua getRuas() {
		return ruas;
	}

	public void setRuas(Rua ruas) {
		this.ruas = ruas;
	}

//	## hasCode and equals

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
	

	
	

}
	