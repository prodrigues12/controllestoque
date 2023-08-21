package com.controlestoque.service;

import java.math.BigDecimal;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

		if (produto.getQtdEstMin() == null) {
			produto.setQtdEstMin(BigDecimal.ONE);
		}
		if (produto.getQtdEstoque() == null) {
			produto.setQtdEstoque(BigDecimal.ZERO);
		}

		prodRepository.save(produto);

		return produto;
	}

	@Transactional
	public void excluir(Produto produto) {

		try {
			prodRepository.delete(produto);
			prodRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel apagar Produto. Existe movimentação.");
		}
	}

	@Transactional
	public void atualizarEstoque(Long codigos, BigDecimal estoque) {

		Produto pro = prodRepository.findByCodigo(codigos);
		if (estoque.compareTo(estoque) < 0) {
			pro.setQtdEstoque(pro.getQtdEstoque().subtract(estoque));
		} else {
			pro.setQtdEstoque(pro.getQtdEstoque().add(estoque));
		}
		prodRepository.save(pro);
	}

	@Transactional
	public void atualizarEstoqueInventario(Long codigos, BigDecimal estoque) {

		Produto pro = prodRepository.findByCodigo(codigos);
		pro.setQtdEstoque(estoque);
		prodRepository.save(pro);

	}
	
	@Transactional
	public void atualizarValorCusto(Long codigos, BigDecimal valorCusto) {

		Produto pro = prodRepository.findByCodigo(codigos);
		pro.setValorCusto(valorCusto);
		prodRepository.save(pro);

	}

}
