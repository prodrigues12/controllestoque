
package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import com.controlestoque.Repository.ProdutoRepository;
import com.controlestoque.model.Produto;
import com.controlestoque.model.Secao;

@Controller
public class EstoqueController {

	@Autowired
	ProdutoRepository proRepository;
	
	@GetMapping("**/consulta-estoque")
	public ModelAndView consutarEstoque() {
		ModelAndView mv = new ModelAndView("estoque/consultaEstoque");
		mv.addObject("listProduto", proRepository.findAll());
		return mv;
	}
	
	@PostMapping("/ajustarEstoque")
	public String alterar(@PathVariable("etq") int etq, Produto produto) {
		produto.setQtdEstoque(etq);
		return"redirect:/consulta-estoque";
	}
	
//	@GetMapping("/alterar/{idProduto}")
//	public ModelAndView alterar(@PathVariable("idProduto") Long idProduto) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("produto/alterarProduto");
//		Produto produto = pr.getById(idProduto);
//		mv.addObject("produto", produto);
//		Iterable<Secao> secao = sr.findAll();
//		mv.addObject("secaoList", secao);
//		return mv;
//		
//	}
}
