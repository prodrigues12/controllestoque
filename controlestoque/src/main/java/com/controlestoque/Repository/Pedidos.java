package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.Repository.helper.pedido.PedidosQueries;
import com.controlestoque.model.Pedido;


public interface Pedidos extends JpaRepository<Pedido, Long>, PedidosQueries {

	@Query("select count(*) from Pedido p where p.status =NOVO")
	public Pedido statusNovo();
}
