package com.controlestoque.controller;

import java.io.Serializable;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Repository.Apartamentos;
import com.controlestoque.Repository.Blocos;
import com.controlestoque.Repository.Ruas;
import com.controlestoque.Repository.filter.ApartamentoFilter;

import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Apartamento;

import com.controlestoque.service.ApartamentoService;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;
import com.controlestoque.service.exception.NomeApartamentoExisteException;

@Controller
@RequestMapping("/apartamento")
public class ApartamentoController implements Serializable {

	@Autowired
	private Blocos blocoRepository;

	@Autowired
	private ApartamentoService apartamentoService;

	@Autowired
	private Apartamentos apartamentosRepository;

	@Autowired
	private Ruas ruaRepository;

	private static final long serialVersionUID = 1L;

	@RequestMapping("/novo")
	public ModelAndView novo(Apartamento apartamento) {
		ModelAndView mv = new ModelAndView("apartamento/apartamentoNovo");
		mv.addObject("bloco", blocoRepository.findAll());
		mv.addObject("rua", ruaRepository.findAll());
		return mv;
	}

	@RequestMapping(value = { "/novo", "{//d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Apartamento apartamento, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(apartamento);
		}
		try {
			apartamentoService.salvar(apartamento);

		} catch (NomeApartamentoExisteException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(apartamento);
		}
		attributes.addFlashAttribute("mensagem", "Apartamento Salvo!");
		return new ModelAndView("redirect:/apartamento/novo");
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Apartamento> pesquisarPorCodigoBloco(
			@RequestParam(name = "bloco", defaultValue = "0") Long condigoBloco) {
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {

		}

		return apartamentosRepository.findByBlocoCodigo(condigoBloco);

	}

	@GetMapping
	public ModelAndView pesquisar(ApartamentoFilter apartamentoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("apartamento/pesquisaApartamento");
		mv.addObject("bloco", blocoRepository.findAll());

		PageWrapper<Apartamento> paginaWrapper = new PageWrapper<>(
				apartamentosRepository.filtrar(apartamentoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;

	}

	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Apartamento apartamento) {
		try {
			apartamentoService.excluir(apartamento);

		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Apartamento apartamento) {
		ModelAndView mv = novo(apartamento);
		mv.addObject(apartamento);
		mv.addObject(mv);
		return mv;
	}

}
