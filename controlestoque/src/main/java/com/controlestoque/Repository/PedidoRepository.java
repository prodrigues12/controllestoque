package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("SELECT MAX(p.idPedido) FROM Pedido p" )
	public Long lastIdPedido();
	
	@Query("SELECT p FROM Pedido p where p.status='Novo'" )
	Iterable<Pedido> listNovos();
//	
}
