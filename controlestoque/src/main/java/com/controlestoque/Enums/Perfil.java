package com.controlestoque.Enums;

public enum Perfil {
	
	ADMINISTRADOR("ADMINISTRADOR"),
	GERENCIA("GERENCIA"),
	FUNCIONARIO("FUNCIONARIO");
	
	private String perfil;
	
	private Perfil (String perfil) {
		this.perfil=perfil;
	}
	
	
}
