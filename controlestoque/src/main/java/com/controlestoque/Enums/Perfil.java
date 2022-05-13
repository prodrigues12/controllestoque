package com.controlestoque.Enums;

public enum Perfil {
	
	FUNCIONÁRIO("FUNCIONARIO"),
	GERÊNCIA("GERENCIA"),
	ADMINISTRADOR("ADMINISTRADOR");
	
	private String perfil;
	
	private Perfil (String perfil) {
		this.perfil=perfil;
	}
}
	
