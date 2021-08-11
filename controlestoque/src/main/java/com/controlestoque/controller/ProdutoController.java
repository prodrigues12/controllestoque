package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.GrupoRepository;
import com.controlestoque.Repository.ItemRepository;
import com.controlestoque.Repository.ProdutoRepository;
import com.controlestoque.Repository.SecaoRepository;
import com.controlestoque.model.Grupo;

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
	
	@Autowired
	GrupoRepository gr;
	
	
	@GetMapping("/lista-produto")
	public ModelAndView listProdutos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("produto/listaProduto");
		mv.addObject("produtoList" ,pr.findAll());
		
		return mv;
	}
	
	@GetMapping("/inserirProduto")
	public ModelAndView insertProduto() {
		ModelAndView mv = new ModelAndView("produto/formProduto");
		mv.addObject("produto", new Produto());
		
		Iterable<Secao> secao = sr.findAll();
		mv.addObject("secaoList", secao);
		
		Iterable<Grupo> grupo = gr.findAll();
		mv.addObject("grupoList", grupo);
		
		return mv;
		
	}

	@PostMapping("salvar-Produto")
	public ModelAndView insertProduto(Produto produto) {
		ModelAndView mv = new ModelAndView();
		pr.save(produto);
		mv.setViewName("redirect:/lista-produto");
		return mv;
	}

	
	
	@GetMapping("{idProduto}")
	public ModelAndView alterar(@PathVariable("idProduto") Long idProduto) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("produto/alterarProduto");
		Produto produto = pr.getById(idProduto);
		
//		Produto pro = pr.findOne(idProduto);
		
		mv.addObject("produto", produto);
		Iterable<Secao> secao = sr.findAll();
		mv.addObject("secaoList", secao);
		Iterable<Grupo> grupo = gr.findAll();
		mv.addObject("grupoList", grupo);
		
		return mv;
		
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Produto produto) {
		ModelAndView mv = new ModelAndView();
		pr.save(produto);
		mv.setViewName("redirect:/lista-produto");
		return mv;
	}
	
	@GetMapping("/excluir-produto/{idProduto}")
	public String excluirProduto(@PathVariable("idProduto") Long id) {
		pr.deleteById(id);
		return "redirect:/lista-produto";
	}
	
	@GetMapping("produtoNome")
	public ModelAndView listaNomeProduto() {
		ModelAndView mv = new ModelAndView("produto/pesquisaProduto");
		return mv;
	}
	
	@PostMapping("/pesquisa-produto")
	public ModelAndView pesquisaProduto(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("produto/pesquisaProduto");
		mv.addObject("produtoListResult", pr.findByNomeContainingIngnoreCase(nomepesquisa));
		mv.addObject("produto", new Produto());
		return mv;
		
	}
		

		 
	
}
