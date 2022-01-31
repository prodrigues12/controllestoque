package com.controlestoque.controller;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Enums.Turno;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.model.Produto;
import com.controlestoque.session.TabelaItemSession;


@Controller
@RequestMapping("/pedido")
public class PedidosController {

	@Autowired
	private Produtos proRepository;

	@Autowired
	private TabelaItemSession tabalaItens;

	@GetMapping("/novo")
	public ModelAndView pedidos() {
		ModelAndView mv = new ModelAndView("pedido/pedidoNovo");
		mv.addObject("uuid", UUID.randomUUID().toString());
		mv.addObject("turno", Turno.values());
		return mv;
	}

	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoProduto, String uuid) {
		
		Produto produto = proRepository.findByCodigo(codigoProduto);
		tabalaItens.adicionarItem(uuid,produto,1);
		
		return mvTabelaPedido(uuid);
	}
	
	@PutMapping("/item/{codigo}")
	public ModelAndView alterarQuantidadeItem(@PathVariable Long codigoProduto, Integer quantidade, String uuid) {
		Produto produto = proRepository.findByCodigo(codigoProduto);
		tabalaItens.alterarQauntidadeItem(uuid, produto, quantidade);
		return mvTabelaPedido(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoProduto}")
	public ModelAndView excluirItem(@PathVariable Long codigoProduto, @PathVariable String uuid) {
		Produto prodto = proRepository.findByCodigo(codigoProduto);
		tabalaItens.excluirItem(uuid, prodto);
		return mvTabelaPedido(uuid);
		
		
	}

	private ModelAndView mvTabelaPedido(String uuid) {
		ModelAndView mv = new ModelAndView("pedido/tabelaItensPedido");
		mv.addObject("itens", tabalaItens.getItens(uuid));
		return mv;
	}
}
