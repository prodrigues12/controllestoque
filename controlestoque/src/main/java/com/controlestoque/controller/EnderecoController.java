package com.controlestoque.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Repository.Enderecos;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.Repository.filter.EnderecoFilter;
import com.controlestoque.Repository.filter.ProdutoFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Endereco;
import com.controlestoque.model.Produto;
import com.controlestoque.service.EnderecoService;
import com.controlestoque.service.exception.EnderecoJaCadastradoException;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService endService;

	@Autowired
	private Produtos proRepository;

	@Autowired
	private Enderecos endRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Endereco endereco) {
		ModelAndView mv = new ModelAndView("endereco/novoEndereco");
	
		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvarEndereco(@Valid Endereco endereco, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(endereco);
		}
		try {
			
			endService.salvandoEndereco(endereco);
			
		} catch (EnderecoJaCadastradoException e) {
			
			result.rejectValue("nomeEndereco", e.getMessage(), e.getMessage());
			return novo(endereco);
		}
		
		attributes.addFlashAttribute("mensagem", "Endereco Salvo com sucesso!");
		return new ModelAndView("redirect:/endereco/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(EnderecoFilter EnderecoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("endereco/pesquisarEndereco");
		mv.addObject("produto", proRepository.findAll());

		PageWrapper<Endereco> paginaWrapper = new PageWrapper<>(endRepository.filtrar(EnderecoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editarEndereco(@PathVariable("codigo") Endereco endereco) {
		ModelAndView mv =  novo(endereco);
		mv.addObject(endereco);
		return mv;
		
	}

}
