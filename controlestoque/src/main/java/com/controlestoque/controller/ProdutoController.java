package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.ItemRepository;
import com.controlestoque.Repository.ProdutoRepository;
import com.controlestoque.Repository.SecaoRepository;
import com.controlestoque.model.Item;
import com.controlestoque.model.Produto;
import com.controlestoque.model.Secao;

@Controller
public class ProdutoController {
	
	@Autowired
	ProdutoRepository pr;
	
	@Autowired
	SecaoRepository sr;
	
	@Autowired
	ItemRepository ir;
	
	@GetMapping("/inserirProduto")
	public ModelAndView insertProduto() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("produto/formProduto");
		mv.addObject("produto", new Produto());
		Iterable<Secao> secao = sr.findAll();
		mv.addObject("secaoList", secao);
		return mv;
		
	}

	@PostMapping("salvar-Produto")
	public ModelAndView insertProduto(Produto produto) {
		ModelAndView mv = new ModelAndView();
		pr.save(produto);
		mv.setViewName("redirect:/lista-produto");
		return mv;
	}
	
	@GetMapping("/lista-produto")
	public ModelAndView listProdutos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("produto/listaProduto");
		mv.addObject("produtoList" ,pr.findAll());
		
		return mv;
	}
	
	@GetMapping("/alterar/{idProduto}")
	public ModelAndView alterar(@PathVariable("idProduto") Long idProduto) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("produto/alterarProduto");
		Produto produto = pr.getById(idProduto);
		mv.addObject("produto", produto);
		Iterable<Secao> secao = sr.findAll();
		mv.addObject("secaoList", secao);
		return mv;
		
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Produto produto) {
		ModelAndView mv = new ModelAndView();
		pr.save(produto);
		mv.setViewName("redirect:/lista-produto");
		return mv;
	}
	
	@GetMapping("/excluir/{idProduto}")
	public String excluirAluno(@PathVariable("idProduto") Long id) {
		pr.deleteById(id);
		return "redirect:/lista-produto";
		
	}
}
