package com.controlestoque.Repository.helper.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.PedidoFilter;
import com.controlestoque.model.Pedido;

public interface PedidosQueries {

	public Page<Pedido> filtrar(PedidoFilter filter, Pageable pageable);

	public Pedido buscarComItens(Long codigo);

}
