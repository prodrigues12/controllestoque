package com.controlestoque.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Enums.UnidadeMedia;
import com.controlestoque.Repository.Grupos;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.Repository.Secoes;
import com.controlestoque.Repository.filter.ProdutoFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Produto;
import com.controlestoque.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	 private Produtos proRepository;
	
	@Autowired
	private Secoes sessaoRepository;
	
	@Autowired
	private Grupos gruRepsitory;
	
	@Autowired
	 ProdutoService prodService;

	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/novoProduto");
		mv.addObject("secao", sessaoRepository.findAll());
		mv.addObject("grupo", gruRepsitory.findAll());
		mv.addObject("uniMedida", UnidadeMedia.values());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrarProduto(@Valid Produto produto, BindingResult result, RedirectAttributes atributes) {

		if (result.hasErrors()) {
			return novo(produto);
		} else {
			prodService.salvar(produto);
			atributes.addFlashAttribute("mensagem", "Salvo com sucesso");
			return new ModelAndView("redirect:/produto/novo");
		}
	}

	@GetMapping
	public ModelAndView pesquisar(ProdutoFilter produtoFilter , BindingResult result,
			@PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produto/pesquisarProduto");
		mv.addObject("secao", sessaoRepository.findAll());
		mv.addObject("grupo", gruRepsitory.findAll());
		mv.addObject("uniMedida", UnidadeMedia.values());
		
PageWrapper<Produto> paginaWrapper = new PageWrapper<>(proRepository.filtrar(produtoFilter, pageable), 
		httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

}
