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

import com.controlestoque.Repository.GrupoRepository;
import com.controlestoque.Repository.ProdutoRepository;
import com.controlestoque.Repository.SecaoRepository;
import com.controlestoque.model.Produto;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoRepository proRepository;
	
	@Autowired
	SecaoRepository sr;
	
	@Autowired
	GrupoRepository gruRepsitory;

	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/novoProduto");
		mv.addObject("secao", sr.findAll());
		mv.addObject("grupo", gruRepsitory.findAll());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrarProduto(@Valid Produto produto, BindingResult result, RedirectAttributes atributes) {

		if (result.hasErrors()) {
			return novo(produto);
		} else {
			proRepository.save(produto);
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
