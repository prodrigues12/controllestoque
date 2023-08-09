package com.controlestoque.dto;

public class ValorMes {
	
	private String produto;
	private Double valorUnitario, quantidade,valor;
	
	public ValorMes() {
		
	}
	
	public ValorMes(String produto, Double valorUnitario, Double quantidade, Double valor) {
		super();
		this.produto = produto;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	/**
	 * @return the produto
	 */
	public String getProduto() {
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(String produto) {
		this.produto = produto;
	}

	/**
	 * @return the valorUnitario
	 */
	public Double getValorUnitario() {
		return valorUnitario;
	}

	/**
	 * @param valorUnitario the valorUnitario to set
	 */
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * @return the quantidade
	 */
	public Double getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
	

}
