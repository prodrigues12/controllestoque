package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Item   implements Serializable{
	private static final long serialVersionUID = 1L;
	
/* public clas Item implements Serializable
	
	private static final long serialVersionUID=1L;
*/
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItem;

	@OneToOne
	private Produto produtos;
	
	@ManyToOne
	private Pedido pedido;
	
	private int qtdSaida;
	
//	###*** Get's and Set's ***###

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Produto getProdutos() {
		return produtos;
	}

	public void setProdutos(Produto produtos) {
		this.produtos = produtos;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQtdSaida() {
		return qtdSaida;
	}

	public void setQtdSaida(int qtdSaida) {
		this.qtdSaida = qtdSaida;
	}
	
	



}
