package com.controlestoque.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Item;




public interface ItemRepository  extends JpaRepository<Item, Long>{
	

//	@Query("select i from Item i, Pedido p, Produto pr where i.produtos=pr.idProduto and p.idPedido = :cod")
	@Query("select i from Item i where i.pedido= :id")
	Iterable<Item>ListProduto(Long id);

//	select produto.nome, qtd_saida from produto, item  where item.produtos_id_produto=produto.id_produto and item.pedido_id_pedido=12;

}
