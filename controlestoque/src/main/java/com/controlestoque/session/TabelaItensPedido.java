package com.controlestoque.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


import com.controlestoque.model.ItemPedido;
import com.controlestoque.model.Produto;

@SessionScope
@Component
public class TabelaItensPedido {

	public List<ItemPedido> itens = new ArrayList<>();

	

	public void adicionarItem(Produto produto, Integer quantidade) {


		Optional<ItemPedido> itemPedidoOptional = buscarItemPorProduto(produto);

		ItemPedido itemPedido = null;

		if (itemPedidoOptional.isPresent()) {
			itemPedido = itemPedidoOptional.get();
			itemPedido.setQuantidade(itemPedido.getQuantidade() + quantidade);
		} else {
			itemPedido = new ItemPedido();
			itemPedido.setProduto(produto);
			itemPedido.setQuantidade(quantidade);

			itens.add(0,itemPedido);
		}
	}
	
	public void alterarQuantidadeItem(Produto produto, Integer quantidade) {
		ItemPedido itemPedido = buscarItemPorProduto(produto).get();
		itemPedido.setQuantidade(quantidade);
	}

	private Optional<ItemPedido> buscarItemPorProduto(Produto produto) {
		return itens.stream().filter(i -> i.getProduto().equals(produto))
				.findAny();
		
	}

	public int total() {
		return itens.size();
	}

	public Object getItens() {

		return itens;
	}

}
