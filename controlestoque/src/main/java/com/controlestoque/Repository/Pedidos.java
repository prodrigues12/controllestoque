package com.controlestoque.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.Repository.helper.pedido.PedidosQueries;
import com.controlestoque.model.Pedido;


public interface Pedidos extends JpaRepository<Pedido, Long>, PedidosQueries {
	
	@Query("select p from Pedido p where p.status = 'NOVO'")
	public List<Pedido> pedidoStatusNovo();

}
