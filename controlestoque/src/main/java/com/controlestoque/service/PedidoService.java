package com.controlestoque.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.controlestoque.Enums.StatusPedido;
import com.controlestoque.Repository.Pedidos;
import com.controlestoque.model.Pedido;
import com.controlestoque.service.event.pedido.PedidoEvent;

@Service
public class PedidoService {

	@Autowired
	private Pedidos pedRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

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
	public void emSeparacao(Pedido pedido) {
		pedido.setStatus(StatusPedido.SEPARACAO);
		salvar(pedido);
	}
	
	@Transactional
	public void pendente(Pedido pedido) {
		pedido.setStatus(StatusPedido.PENDENTE);
		salvar(pedido);
	}

	@Transactional
	public void cancelar(Pedido pedido) {
		Pedido pedidoExistente = pedRepository.getById(pedido.getCodigo());

		pedidoExistente.setStatus(StatusPedido.CANCELADO);
		pedRepository.save(pedidoExistente);
	}

	@Transactional
	public void finalizar(Pedido pedido) {
		pedido.setStatus(StatusPedido.FINALIZADO);
		salvar(pedido);
		System.out.println("Service");
		publisher.publishEvent(new PedidoEvent(pedido));
	}

}