package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("SELECT MAX(p.idPedido) FROM Pedido p" )
	public Long lastIdPedido();
	
	@Query("SELECT p FROM Pedido p where p.status='NOVO'" )
	Iterable<Pedido> findAllNovo();
	
	@Query("SELECT p FROM Pedido p where p.status='ABERTO'" )
	Iterable<Pedido> findAllAberto();
	
	@Query("SELECT p FROM Pedido p where p.status='EM ANALISE'" )
	Iterable<Pedido> findAllAnalise();
	
	@Query("SELECT p FROM Pedido p where p.status='CONCLUIDO'" )
	Iterable<Pedido> findAllConcluido();
	
	@Query("select p from Pedido p where p.idPedido = :ped")
	Iterable<Pedido> pesquisaPedido(Long ped);

}
