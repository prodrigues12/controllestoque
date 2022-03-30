package com.controlestoque.Repository.helper.pedido;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.Repository.filter.PedidoFilter;
import com.controlestoque.model.Pedido;

public interface PedidosQueries {

	public Page<Pedido> filtrar(PedidoFilter filter, Pageable pageable);

	public Pedido buscarComItens(Long codigo);

	public Long statusIgualNovo();

	public Long statusIgualFinalizado();

	public Long statusIgualEspera();

	public Long statusIgualCancelado();

}
