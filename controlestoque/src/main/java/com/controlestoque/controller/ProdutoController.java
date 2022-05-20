package com.controlestoque.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Enums.UnidadeMedia;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.Repository.Secoes;
import com.controlestoque.Repository.filter.ProdutoFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.dto.ProdutoDTO;
import com.controlestoque.dto.ProdutosTopFive;
import com.controlestoque.model.Produto;
import com.controlestoque.service.ProdutoService;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private Produtos proRepository;

	@Autowired
	private Secoes sessaoRepository;



	@Autowired
	private ProdutoService prodService;

	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/novoProduto");
		mv.addObject("secao", sessaoRepository.findAll());
		mv.addObject("uniMedida", UnidadeMedia.values());

		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvarProduto(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(produto);
		} else {

			prodService.salvar(produto);
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
			return new ModelAndView("redirect:/produto/novo");
		}
	}

	@GetMapping
	public ModelAndView pesquisar(ProdutoFilter produtoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produto/pesquisarProduto");
		mv.addObject("secao", sessaoRepository.findAll());

		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(proRepository.filtrar(produtoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	//autocomplete 
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProdutoDTO> pesquisar(String codigoOuNome) {
		return proRepository.codigoOuNome(codigoOuNome);
	}

	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Produto produto) {
		try {
			prodService.excluir(produto);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Produto produto) {

		ModelAndView mv = novo(produto);
		mv.addObject(produto);
		mv.addObject("uniMedida", UnidadeMedia.values());
		mv.addObject(mv);
		return mv;

	}
	
	@GetMapping("/estoqueBaixo")
	public ModelAndView estoqueBaixo(ProdutoFilter produtoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produto/estoqueMinimo");
		mv.addObject("secao", sessaoRepository.findAll());

		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(proRepository.estoqueMinimo(produtoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/estoqueZero")
	public ModelAndView estoqueZero(ProdutoFilter produtoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produto/estoqueZero");
		mv.addObject("secao", sessaoRepository.findAll());

		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(proRepository.estoqueZerado(produtoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/produtosTopFive")
	public @ResponseBody List<ProdutosTopFive> listaProdutosTopFive(){
		return proRepository.topFiveProdutos();
		
	}
	

}
