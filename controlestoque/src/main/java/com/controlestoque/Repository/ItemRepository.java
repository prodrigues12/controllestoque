package com.controlestoque.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Item;
import com.controlestoque.model.Produto;


public interface ItemRepository  extends JpaRepository<Item, Long>{
	

	@Query("select i from Item i, Pedido p where p.idPedido=(select MAX(p.idPedido) from p) and p.idPedido=i.pedido ")
	Iterable<Item>ListProduto();
	
//	@Modifying (para usar i INSERT)
//	@Query(value = "select * from Produto p , Item i, Pedido pd where pd.idPedido = i.pedido and i.produtos = p.idProduto and pd.idPedido=(select MAX(pd.idPedido) from pd)")
//	Produto nomeProduto();
	
}
