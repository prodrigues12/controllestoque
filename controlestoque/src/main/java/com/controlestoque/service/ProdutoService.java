package com.controlestoque.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Produtos;
import com.controlestoque.model.Produto;

@Service
public class ProdutoService {
	
	@Autowired
	private Produtos produtoRepository;
	
	
	public Produto salvar (Produto produto) {
		
		if(produto.getQtdEstMin()== null) {
			produto.setQtdEstMin(BigDecimal.ONE);
		}
		
		produtoRepository.save(produto);
		
		return produto;
	}

}
