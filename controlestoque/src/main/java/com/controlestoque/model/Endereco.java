package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Entity
public class Endereco  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull( message = "Campo 'Rua' deve ser preenchido")
	private String rua;
	
	@NotNull( message = "Campo 'Bloco' deve ser preenchido")
	private int bloco;
	
	@NotNull( message = "Campo 'Sala' deve ser preenchido")
	private int sala;
	
	private int apartamento;
	
	@OneToMany
	private Produto produto;
	

	
	

}
