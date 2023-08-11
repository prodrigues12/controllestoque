package com.controlestoque.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Enums.TipoAjusteEstoque;
import com.controlestoque.Repository.Estoques;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.model.Estoque;
import com.controlestoque.model.Produto;

@Service
public class EstoqueService  {
	
	@Autowired
	private Estoques estRepository;
	
	@Autowired
	private Produtos prodRepository;
	
	@Transactional
	public Estoque atualizarEstoque(Long codigos, BigDecimal estoque) {
		System.out.println("chegou no atualizar estoque");
		Estoque novoEstoque = new Estoque();
		Produto pro = prodRepository.findByCodigo(codigos);
		
		novoEstoque.setData(LocalDate.now());
		novoEstoque.setProduto(pro);
		novoEstoque.setQuantidade(estoque);
		novoEstoque.setTipoAjuste(TipoAjusteEstoque.ENTRADA);
		
		
		estRepository.save(novoEstoque);
		
		return novoEstoque;
	}
	

}
