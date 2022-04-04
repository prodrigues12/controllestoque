package com.controlestoque.service.event.pedido;

import com.controlestoque.model.Pedido;

public class PedidoEvent {
	
	private Pedido pedido;
	
	public PedidoEvent(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}
		

}
