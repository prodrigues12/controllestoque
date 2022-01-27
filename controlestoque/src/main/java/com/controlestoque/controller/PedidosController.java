package com.controlestoque.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.controlestoque.session.TabelaItensPedido;

@Controller
@RequestMapping("/pedido")
public class PedidosController {

	@Autowired
	private Produtos proRepository;

	@Autowired
	private TabelaItensPedido tabalaItensPedido;

	@GetMapping("/novo")
	public ModelAndView pedidos() {
		ModelAndView mv = new ModelAndView("pedido/pedidoNovo");
		mv.addObject("turno", Turno.values());
		return mv;
	}

	@PostMapping("/item")
	public @ResponseBody ModelAndView adicionarItem(Long codigoProduto) {
		
		Produto produto = proRepository.findByCodigo(codigoProduto);
		tabalaItensPedido.adicionarItem(produto,1);
		
		ModelAndView mv = new ModelAndView("pedido/tabelaItensPedido");
		mv.addObject("itens", tabalaItensPedido.getItens());
		return mv;
	}
	
	@PutMapping("/item/{codigo}")
	public ModelAndView alterarQuantidadeItem(@PathVariable Long codigo, Integer quantidade) {
		Produto produto = proRepository.findByCodigo(codigo);
		tabalaItensPedido.alterarQuantidadeItem(produto, quantidade);
		ModelAndView mv = new ModelAndView("pedido/tabelaItensPedido");
		mv.addObject("itens", tabalaItensPedido.getItens());
		return mv;
	}
}
