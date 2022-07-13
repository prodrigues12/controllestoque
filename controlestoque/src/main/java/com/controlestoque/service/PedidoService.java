package com.controlestoque.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
			pedido.setStatus(StatusPedido.NOVO);
		} else {
			Pedido pedidoExistente = pedRepository.getById(pedido.getCodigo());
			pedido.setDataCriacao(pedidoExistente.getDataCriacao());
		}
		
		pedido.setDataModifucacao(LocalDate.now());
		pedRepository.save(pedido);
	}
	
	@Transactional
	public void emSeparacao(Pedido pedido) {
		Pedido pedidoExistente = pedRepository.getById(pedido.getCodigo());

		pedidoExistente.setStatus(StatusPedido.SEPARACAO);
		pedRepository.save(pedidoExistente);
	}
	
	@Transactional
	public void pendente(Pedido pedido) {
		Pedido pedidoExistente = pedRepository.getById(pedido.getCodigo());

		pedidoExistente.setStatus(StatusPedido.PENDENTE);
		pedRepository.save(pedidoExistente);
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