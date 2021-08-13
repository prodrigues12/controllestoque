package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.PedidoRepository;
import com.controlestoque.Repository.ProdutoRepository;
import com.controlestoque.model.Pedido;
import com.controlestoque.model.Produto;


@Controller
public class HomeController {
	
	@Autowired
	PedidoRepository pedRepository;
	@Autowired
	ProdutoRepository proRepository;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		return mv;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/login");
		return mv;
	}
	
	@GetMapping("/pedido")
	public ModelAndView pedido() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pedido/formPedido");
		return mv;
		
	
	}
	
	@GetMapping("/admin")
	public ModelAndView admController() {
	ModelAndView mv = new ModelAndView("home/admin");
	Iterable<Pedido> pedido = pedRepository.findAllNovo();
	mv.addObject("pedidoListNovo", pedido);
	
	Iterable<Produto> produto = proRepository.baixoEtq();
	mv.addObject("produtoList", produto);
	
	Iterable<Pedido> pedido2 = pedRepository.findAllAnalise();
	mv.addObject("pedidoListAnalise", pedido2);

	return mv;
	
	}
}
