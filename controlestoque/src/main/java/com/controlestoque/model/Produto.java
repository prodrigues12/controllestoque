package com.controlestoque.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.NumberFormat;

import com.controlestoque.Enums.UnidadeMedia;
import com.controlestoque.dto.PedidosMes;
import com.controlestoque.dto.ProdutosTopFive;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

@DynamicUpdate
@SqlResultSetMapping(name = "mappingProtutosFive", classes = @ConstructorResult(targetClass = ProdutosTopFive.class, columns = {
		@ColumnResult(name = "nome", type = String.class), @ColumnResult(name = "quantidade", type = Integer.class) }))

public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Size(min = 7, max = 100, message = "O campo ''Nome'' deve contar de 7 à 100 caracteries")
	@NotBlank
	private String nome;

	private String descricao;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal qtdEstoque;

	@DecimalMin(value = "1.0", message = "Estoque deve ser no mínimo 1")
	private BigDecimal qtdEstMin;

	@NotNull(message = "Campo 'Seção' é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_secao")
	private Secao secao;

	@NotNull(message = "O unidade de medida é obrigatório")
	@Enumerated(EnumType.STRING)
	private UnidadeMedia uniMedida;

	@OneToMany(mappedBy = "produto")
	private List<Enderecar> enderecar;

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
		this.nome = nome.toUpperCase();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
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

	public UnidadeMedia getUniMedida() {
		return uniMedida;
	}

	public void setUniMedida(UnidadeMedia uniMedida) {
		this.uniMedida = uniMedida;
	}

	public List<Enderecar> getEnderecar() {
		return enderecar;
	}

	public void setEnderecar(List<Enderecar> enderecar) {
		this.enderecar = enderecar;
	}

	public boolean isProdutoNovo() {
		return this.codigo == null;
	}

	public String getEstoqueIgualMenorZero() {
		if (getQtdEstoque().compareTo(BigDecimal.ZERO) == 0 || getQtdEstoque().compareTo(BigDecimal.ZERO) == -1) {
			return "valor-negativo";
		}
		
		return null;

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
