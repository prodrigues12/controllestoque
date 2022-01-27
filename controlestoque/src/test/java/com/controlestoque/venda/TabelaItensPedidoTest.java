package com.controlestoque.venda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.controlestoque.model.ItemPedido;
import com.controlestoque.model.Produto;
import com.controlestoque.session.TabelaItensPedido;

public class TabelaItensPedidoTest {
	
	private TabelaItensPedido tabelaItensPedido;
	
	@Before
	public void setUp() {
		this.tabelaItensPedido = new TabelaItensPedido();
	}
	
	@Test
	public void deveManterTamanhoListaMesmoProduto()  throws Exception{
		Produto p1 = new Produto();
		p1.setCodigo(1L);
		
		tabelaItensPedido.adicionarItem(p1, 1);
		tabelaItensPedido.adicionarItem(p1, 1);
		
		assertEquals(1, tabelaItensPedido.total());
		
	}


}
