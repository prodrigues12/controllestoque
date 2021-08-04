package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("SELECT MAX(p.idPedido) FROM Pedido p" )
	public Long lastIdPedido();
	
	@Query("SELECT p FROM Pedido p where p.status='NOVO'" )
	Iterable<Pedido> listNovos();
	
	@Query("SELECT p FROM Pedido p where p.status='ANALISE'" )
	Iterable<Pedido> listAnalise();
	
	@Query("select p from Pedido p where p.idPedido = :ped")
	Iterable<Pedido> pesquisaPedido(Long ped);
//	
}
