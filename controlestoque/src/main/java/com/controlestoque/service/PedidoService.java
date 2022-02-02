package com.controlestoque.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	public void salvar(Pedido pedido) {
		if(pedido.isNovo()) {
			pedido.setDataCriacao(LocalDate.now());
		}
		
		pedRepository.save(pedido);
	}

}