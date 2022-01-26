package com.controlestoque.session;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.controlestoque.model.ItemPedido;
import com.controlestoque.model.Produto;

//@SessionScope
@Component
public class TabelaItensPedido {
	
	private List<ItemPedido> itens;
	
	public void adicionarItem(Produto produto , BigDecimal quantidade) {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(quantidade);
		
		itens.add(itemPedido);
	}
	
	public int total() {
		return itens.size();
	}

}
