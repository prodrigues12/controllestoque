package com.controlestoque.venda;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.controlestoque.model.ItemPedido;
import com.controlestoque.model.Produto;

@SessionScope
@Component
public class TabelaItensPedido {
	

	
	public void adicionarItem(Produto produto , Integer quantidade) {
		ItemPedido itemPedio = new ItemPedido();
		itemPedio.setProduto(produto);
		itemPedio.setQuantidade(quantidade);
	}

}
