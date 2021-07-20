
package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.controlestoque.Repository.ProdutoRepository;
import com.controlestoque.model.Produto;
import com.controlestoque.model.Secao;

@Controller
public class EstoqueController {

	@Autowired
	ProdutoRepository proRepository;
	
	@GetMapping("/estoque")
	public ModelAndView consutarEstoque() {
		ModelAndView mv = new ModelAndView("estoque/estoque");
		Iterable<Produto> produto = proRepository.findAll();
		mv.addObject("produtoList", produto);
		return mv;
	}

//	@GetMapping(path = {"/{id}"})
	@GetMapping("/ajustaEstoque/{id}") 
	public ModelAndView ajustaEstoque(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("estoque/ajustaEstoque");
		Produto produto = proRepository.getOne(id);
		mv.addObject("produto" , produto);
		return mv;
		}
	
	@PostMapping("/ajustarEstoque")
	public ModelAndView ajustarEstoque(Produto produto) {
		ModelAndView mv = new ModelAndView("redirect:/estoque");
		
		Produto temp = new Produto ();
		temp = proRepository.AjusteEtq(produto.getIdProduto());
		temp.setQtdEstoque(produto.getQtdEstoque());
		produto = temp;
		proRepository.save(produto);
		
		
		
		return mv;
	}
	
	@PostMapping("/pesquisa-estoque")
	public ModelAndView pesquisaEst(@RequestParam("nomePesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("estoque/pesquisaEstoque");
		mv.addObject("estListResult", proRepository.findByNomeContainingIngnoreCase(nomepesquisa));
		mv.addObject("produto", new Produto());
		return mv;
	}

	
//	@PostMapping("**/pesquisa-secao")
//	public ModelAndView pesquisarSecao(@RequestParam("nomepesquisa") String nomepesquisa) {
//		ModelAndView mv = new ModelAndView("secao/pesquisaSecao");
//		mv.addObject("secaoListResult", sr.findByNomeContainingIngnoreCase(nomepesquisa));
//		mv.addObject("secao" , new Secao());
//		return mv;
//	}
	
}
