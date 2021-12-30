package com.controlestoque.service;

import java.math.BigDecimal;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.controlestoque.Repository.Produtos;
import com.controlestoque.model.Produto;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class ProdutoService {
	
	@Autowired
	private Produtos prodRepository;
	
	@Transactional
	public Produto salvar(Produto produto) {
		
		if(produto.getQtdEstMin()== null) {
			produto.setQtdEstMin(BigDecimal.ONE);
		}
		if(produto.getQtdEstoque()== null) {
			produto.setQtdEstoque(BigDecimal.ZERO);
		}
		
//		System.out.println(">>>>>>>: "+ produto.getAgrupar().getSubgrupo().getCodigo());
		prodRepository.save(produto);
		
		return produto;
	}
	
	@Transactional
	public void excluir(Produto produto) {
		
		try {

		prodRepository.delete(produto);
		prodRepository.flush();
	}catch (PersistenceException e) {
		throw new ImpossivelExcluirEntidadeException("Impossivel apagar Produto. Existe movimentação.");
	}
	}

}
