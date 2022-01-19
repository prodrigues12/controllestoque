package com.controlestoque.Repository.filter;

import java.util.List;

import com.controlestoque.model.GrupoUser;

public class UsuarioFilter {
	
	private Long codigo;
	
	private String nome;

	private String email;

	private List<GrupoUser> grupoUser;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<GrupoUser> getGrupoUser() {
		return grupoUser;
	}

	public void setGrupoUser(List<GrupoUser> grupoUser) {
		this.grupoUser = grupoUser;
	}
	
	

}
