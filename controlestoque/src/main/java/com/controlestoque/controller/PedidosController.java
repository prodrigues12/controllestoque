package com.controlestoque.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping
	public ModelAndView pedidos() {
		ModelAndView mv= new ModelAndView("pedido/pedidoNovo");
		return mv;
	}

	@PostMapping("/item")
	public @ResponseBody String adicionarItem(Long codigoProduto) {
		System.out.println(codigoProduto);
		Produto produto = proRepository.findByCodigo(codigoProduto);
		System.out.println(produto.getNome());
		tabalaItensPedido.adicionarItem(produto,new BigDecimal(1) );
		System.out.println(">>>>>>> total itens: " + tabalaItensPedido.total());
		return "item add";
	}
}
