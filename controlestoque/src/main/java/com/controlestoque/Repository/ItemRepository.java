package com.controlestoque.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Item;



public interface ItemRepository  extends JpaRepository<Item, Long>{
	

	@Query("select i.idItem from Item as i, Pedido as r where i.pedido=r.idPedido and r.idPedido=9")
	public Iterable<Item>ListProduto(); 

}
//select produto.nome from produto, item  where item.produtos_id_produto=produto.id_produto and item.pedido_id_pedido=9;

//@Query("select i.idItem from Item as i, Pedido as r where i.pedido=r.idPedido and r.idPedido=9")