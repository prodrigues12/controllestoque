package com.controlestoque.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Enums.TipoAjusteEstoque;
import com.controlestoque.Repository.Estoques;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.Repository.ValorCustos;
import com.controlestoque.model.Estoque;
import com.controlestoque.model.Produto;
import com.controlestoque.model.ValorCusto;

@Service
public class ValorCustoService {

	@Autowired
	private ValorCustos valRepository;

	@Autowired
	private Produtos prodRepository;
	
	@Autowired
	private ProdutoService proService;

	@Transactional
	public ValorCusto atualizarValorCusto(ValorCusto valorCusto, Long codigos) {

		ValorCusto novoValorCusto = new ValorCusto();
		Produto pro = prodRepository.findByCodigo(codigos);

		novoValorCusto.setDataAlteracao(LocalDate.now());
		novoValorCusto.setValorCusto(valorCusto.getValorCusto());
		novoValorCusto.setProduto(pro);
		novoValorCusto.setFornecedor(valorCusto.getFornecedor());
		
		proService.atualizarValorCusto(pro.getCodigo(),novoValorCusto.getValorCusto());
		
		valRepository.save(novoValorCusto);

		return novoValorCusto;
	}

}
