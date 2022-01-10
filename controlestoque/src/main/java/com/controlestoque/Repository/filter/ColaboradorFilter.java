package com.controlestoque.Repository.filter;

import com.controlestoque.Enums.TipoIdentificacao;

public class ColaboradorFilter {
	
	private Long codigo;
	private String nome;
	private String cpfCnpjId;
	private TipoIdentificacao tipoIdentificacao;
	
	
	
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
	public String getCpfCnpjId() {
		return cpfCnpjId;
	}
	public void setCpfCnpjId(String cpfCnpjId) {
		this.cpfCnpjId = cpfCnpjId;
	}
	
	public TipoIdentificacao getTipoIdentificacao() {
		return tipoIdentificacao;
	}
	public void setTipoIdentificacao(TipoIdentificacao tipoIdentificacao) {
		this.tipoIdentificacao = tipoIdentificacao;
	}
	
	public Object getCpfCnpjIdSemFormtacao() {
		return TipoIdentificacao.removerFormatacao(this.cpfCnpjId);
	}
	
	

}
