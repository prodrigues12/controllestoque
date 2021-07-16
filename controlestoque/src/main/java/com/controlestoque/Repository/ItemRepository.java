package com.controlestoque.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Item;




public interface ItemRepository  extends JpaRepository<Item, Long>{
	

	@Query("select i from Item i, Pedido p where p.idPedido=(select MAX(p.idPedido) from p) and p.idPedido=i.pedido ")

	Iterable<Item>ListProduto();
}
