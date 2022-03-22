package com.controlestoque.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.controlestoque.Enums.StatusPedido;
import com.controlestoque.Repository.Pedidos;
import com.controlestoque.model.Pedido;

@Service
public class PedidoService {

	@Autowired
	private Pedidos pedRepository;

	@Transactional
	public void salvar(Pedido pedido) {
		
		if (pedido.isPedidoNovo()) {
			pedido.setDataCriacao(LocalDate.now());
			
		} else {
			Pedido pedidoExistente = pedRepository.getById(pedido.getCodigo());
			pedido.setDataCriacao(pedidoExistente.getDataCriacao());

		}

		 pedRepository.save(pedido);
	}
	
	
	@Transactional
	public void cancelar(Pedido pedido) {
		Pedido pedidoExistente = pedRepository.getById(pedido.getCodigo());

		pedidoExistente.setStatus(StatusPedido.CANCELADO);
		pedRepository.save(pedidoExistente);
	}

}