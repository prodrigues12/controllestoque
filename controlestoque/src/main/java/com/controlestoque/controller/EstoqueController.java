package com.controlestoque.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Enums.UnidadeMedia;
import com.controlestoque.Repository.Grupos;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.Repository.Secoes;
import com.controlestoque.Repository.filter.ProdutoFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Produto;
import com.controlestoque.service.ProdutoService;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {
	
	@Autowired
	private Produtos proRepository;

	@Autowired
	private Secoes sessaoRepository;

	@Autowired
	private Grupos gruRepsitory;
	
	@Autowired
	private ProdutoService prodService;
	
	
	@GetMapping
	public ModelAndView estoque(ProdutoFilter produtoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("estoque/estoque");
		mv.addObject("uniMedida", UnidadeMedia.values());
		mv.addObject("secao", sessaoRepository.findAll());
		mv.addObject("grupos", gruRepsitory.findAll());

		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(proRepository.filtrar(produtoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;

	}

	
	@GetMapping("/ajuste/{codigo}")
	public ModelAndView editarEstoque(@PathVariable("codigo") Produto produto) {
		ModelAndView mv = new ModelAndView("estoque/ajusteEstoque");
	
		mv.addObject(produto);
		mv.addObject("uniMedida", UnidadeMedia.values());
		mv.addObject("secao", sessaoRepository.findAll());
		mv.addObject("grupos", gruRepsitory.findAll());
		
		mv.addObject(mv);
		return mv;
	}

	@RequestMapping(value = "/ajuste/{codigo}", method = RequestMethod.POST)
	public ModelAndView novoEstoque(@RequestParam("codigo") Long codigo,
			@RequestParam("qtdEstoque") BigDecimal estoque  ) {

	
		prodService.atualizarEstoque(codigo, estoque);
			
			return new ModelAndView("redirect:/estoque");
//		}
	}

}



