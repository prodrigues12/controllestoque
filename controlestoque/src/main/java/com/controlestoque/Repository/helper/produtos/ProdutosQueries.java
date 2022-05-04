package com.controlestoque.Repository.helper.produtos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.ProdutoFilter;
import com.controlestoque.dto.ProdutoDTO;
import com.controlestoque.model.Produto;

public interface ProdutosQueries {

	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);

	public List<ProdutoDTO> codigoOuNome(String codigoOuNome);

	public Produto buscaCompleta(Long codigo);

	public BigDecimal totalItensEstoque();

	public Long estoqueBaixo();

	public Long estoqueZero();
	
	public Page<Produto> estoqueMinimo(ProdutoFilter filtro, Pageable pageable);

	public Page<Produto> estoqueZerado(ProdutoFilter filtro, Pageable pageable);
}
