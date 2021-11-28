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
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;
	
//	@Size(min = 7, max = 40, message = "Nome do PRODUTO deve contar no minimo 7 caracteres no max 40")
	@NotBlank
	private String nome;
	
	private String descricao;
	
	@NumberFormat (pattern = "#,##0.00")
	private BigDecimal qtdEstoque;
	

//	@NotNull(message = "O unidade de medida é obrigatório")
//	@ManyToOne
//	@JoinColumn(name = "codigo_estilo")
//	private UnidadeMedida unidadeMedia;
	
//	@NotNull(message = "Campo é obrigatória")
	@DecimalMin(value = "1.0", message = "Estoque deve ser no mínimo 1")
	@NumberFormat (pattern = "#,##0.00")
	private BigDecimal qtdEstMin;

	@NotNull(message = "A seção é obrigatório")
//	@ManyToOne
//	@JoinColumn(name = "codigo_secao")
//	private Secao secoes;
//	
//	@ManyToOne
//	private Grupo grupos;
	
//	@JsonIgnore
//	@Embedded
//	private Endereco endereco;
	
//	## Construtor
	
	public Produto() {
		
	}


//	## Get's and Set's


	

//	## hasCode and equals

	
	
	

}
	