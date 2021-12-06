package com.controlestoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Enums.UnidadeMedia;
import com.controlestoque.Repository.Grupos;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.Repository.Secoes;
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
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("produto/pesquisarProduto");
		return mv;
	}

}
