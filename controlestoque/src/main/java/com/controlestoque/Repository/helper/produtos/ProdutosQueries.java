package com.controlestoque.Repository.helper.produtos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.controlestoque.Repository.filter.ProdutoFilter;
import com.controlestoque.model.Produto;

public interface ProdutosQueries {

	public Page<Produto>filtrar(ProdutoFilter filtro, Pageable pageable);
	
//	public List<ProdutoDTO> porNome(String porNome);
}