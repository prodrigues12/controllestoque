package com.controlestoque.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Item;
import com.controlestoque.model.Produto;




public interface ItemRepository  extends JpaRepository<Item, Long>{
	

	@Query("select i from Item i, Pedido p where p.idPedido=(select MAX(p.idPedido) from p) and p.idPedido=i.pedido ")
	Iterable<Item>ListProduto();
	
	@Query("select p.nome from Produto p , Item i, Pedido pd where pd.idPedido = i.pedido and i.produtos = p.idProduto and pd.idPedido=(select MAX(pd.idPedido) from pd)")
	Produto nomeProduto();
	
}
//	select produto.nome from produto, pedido, item where pedido.id_pedido = item.pedido_id_pedido and item.produtos_id_produto= produto.id_produto and pedido.id_pedido=(SELECT MAX(id_pedido) FROM pedido);
