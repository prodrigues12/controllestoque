package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Email;

import com.controlestoque.Enums.TipoColaborador;

@Entity
public class Colaborador implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "Campo nome é obrigatório")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa")
	private TipoColaborador tipoColaborador;
	
	
	private String matriculaOuCpf;
	
	@Email
	private String email;

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

	public TipoColaborador getTipoColaborador() {
		return tipoColaborador;
	}

	public void setTipoColaborador(TipoColaborador tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}

	public String getMatriculaOuCpf() {
		return matriculaOuCpf;
	}

	public void setMatriculaOuCpf(String matriculaOuCpf) {
		this.matriculaOuCpf = matriculaOuCpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	


}
