package com.controlestoque.service.event.pedido;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.controlestoque.Repository.Produtos;
import com.controlestoque.model.ItemPedido;
import com.controlestoque.model.Produto;

@Component
public class PedidoListener {

	@Autowired
	private Produtos proRepository;

	@EventListener
	public void pedidoFinalizado(PedidoEvent pedidoEvent) {
		for (ItemPedido item : pedidoEvent.getPedido().getItens()) {
			Produto produto = proRepository.findByCodigo(item.getPedido().getCodigo());
			produto.setQtdEstoque(produto.getQtdEstoque().subtract(new BigDecimal(item.getQuantidade())));
			proRepository.save(produto);
		}
	}

}
