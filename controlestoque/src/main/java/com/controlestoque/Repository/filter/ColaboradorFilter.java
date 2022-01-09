package com.controlestoque.Repository.filter;

import com.controlestoque.Enums.TipoSolicitante;

public class ColaboradorFilter {
	
	private long codigo;
	private String nome;
	private String cpfCnpjId;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfCnpjId() {
		return cpfCnpjId;
	}
	public void setCpfCnpjId(String cpfCnpjId) {
		this.cpfCnpjId = cpfCnpjId;
	}
	
	public Object getCpfCnpjIdSemFormtacao() {
		return TipoSolicitante.removerFormatacao(this.cpfCnpjId);
	}
	
	

}
