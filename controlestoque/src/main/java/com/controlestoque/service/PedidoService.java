package com.controlestoque.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Pedidos;
import com.controlestoque.model.Pedido;

@Service
public class PedidoService {

	@Autowired
	private Pedidos pedRepository;

	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isPedidoNovo()) {
			pedido.setDataCriacao(LocalDate.now());
		} else {
			Pedido pedidoExistente = pedRepository.getById(pedido.getCodigo());
			pedido.setDataCriacao(pedidoExistente.getDataCriacao());

		}

		return pedRepository.saveAndFlush(pedido);
	}

}