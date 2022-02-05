package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlestoque.Repository.helper.pedido.PedidosQueries;
import com.controlestoque.model.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Long>, PedidosQueries {

}
