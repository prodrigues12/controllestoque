package com.controlestoque.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.controlestoque.Enums.Perfil;

@Entity
public class Funcionario {

	@Id
	@NotNull(message = "Campo ID obrigatorio")
//	@NotEmpty (message = "Campo ID obrigatorio")
	private Long idFuncionario;

	@Size(min = 7, max = 40, message = "Nome deve contar no minimo 7 caracteres")
	@NotEmpty(message = "Campo NOME deve ser preenchido.")
	@NotNull(message = "nullllll  Campo NOME deve ser preenchido.")
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
