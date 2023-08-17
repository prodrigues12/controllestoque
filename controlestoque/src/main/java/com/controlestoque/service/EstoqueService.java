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
public class EstoqueService {

	@Autowired
	private Estoques estRepository;

	@Autowired
	private Produtos prodRepository;

	@Autowired
	private ProdutoService prodService;

	@Transactional
	public Estoque atualizarEstoque(Estoque estoque, Long codigos) {

		Estoque novoEstoque = new Estoque();
		Produto pro = prodRepository.findByCodigo(codigos);

		//VALIDANDO TIPO DE AJUSTE
		if (estoque.getTipoAjuste().equals(TipoAjusteEstoque.ENTRADA)
				|| estoque.getTipoAjuste().equals(TipoAjusteEstoque.OUTRAS_ENTRADAS)) {
			novoEstoque.setQuantidade(estoque.getQuantidade());
			prodService.atualizarEstoque(pro.getCodigo(), estoque.getQuantidade());
			
		} else {
			novoEstoque.setQuantidade(estoque.getQuantidade().negate());
			prodService.atualizarEstoque(pro.getCodigo(), estoque.getQuantidade().negate());
		}

		novoEstoque.setData(LocalDate.now());
		novoEstoque.setProduto(pro);
		novoEstoque.setTipoAjuste(estoque.getTipoAjuste());
		estRepository.save(novoEstoque);

		return novoEstoque;
	}

}
