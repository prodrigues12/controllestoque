package com.controlestoque.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.controlestoque.Enums.Perfil;

@Entity
public class Funcionario {

	@Id
	private Long idFuncionario;

	
	private String nomeFuncionario;

	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	
	

//	Get's and Set's

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long id) {
		this.idFuncionario = id;
	}

	
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
