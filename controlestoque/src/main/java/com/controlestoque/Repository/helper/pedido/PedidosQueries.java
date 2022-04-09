package com.controlestoque.Repository.helper.pedido;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.PedidoFilter;
import com.controlestoque.dto.PedidosMes;
import com.controlestoque.model.Pedido;

public interface PedidosQueries {

	public Page<Pedido> filtrar(PedidoFilter filter, Pageable pageable);

	public Pedido buscarComItens(Long codigo);

	public Long statusIgualNovo();

	public Long statusIgualFinalizado();

	public Long statusIgualPendente();

	public Long statusIgualCancelado();
	
	public Long statusIgualSeparando();

	
	public List<PedidosMes> totalPorMes();

	Page<Pedido> filtrarPedidoNovo(PedidoFilter filter, Pageable pageable);

	
}
