package com.controlestoque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Item;
import com.controlestoque.model.Pedido;


public interface ItemRepository  extends JpaRepository<Item, Long>{
	

	@Query("select p.nome from Produto p, Item i where i.produtos = p.idProduto and i.pedido = 10")
	public List<Item>ListProduto(); 

}
//select produto.nome from produto, item  where item.produtos_id_produto=produto.id_produto and item.pedido_id_pedido=9;