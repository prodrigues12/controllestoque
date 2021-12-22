package com.controlestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.model.Produto;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

	
	@GetMapping
	public ModelAndView estoque (Produto produto) {
		ModelAndView mv = new ModelAndView("estoque/estoque");
	
		return mv;
	}
	
	@RequestMapping("/ajusteEstoque")
	public ModelAndView estoqueAjuste (Produto produto) {
		ModelAndView mv = new ModelAndView("estoque/ajusteEstoque");
	
		return mv;
	}
}
