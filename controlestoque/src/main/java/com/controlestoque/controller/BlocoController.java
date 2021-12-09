package com.controlestoque.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Repository.Blocos;
import com.controlestoque.Repository.Ruas;
import com.controlestoque.model.Bloco;
import com.controlestoque.service.BlocoService;
import com.controlestoque.service.exception.NomeBlocoExistenteException;



@Controller
@RequestMapping("/bloco")
public class BlocoController implements Serializable {

	@Autowired
	private Ruas ruaRepository;

	@Autowired
	private BlocoService blocoService;
	
	@Autowired
	private Blocos blocoRepository;

	private static final long serialVersionUID = 1L;

	@RequestMapping("/novo")
	public ModelAndView novo(Bloco bloco) {
		ModelAndView mv = new ModelAndView("bloco/blocoNovo");
		mv.addObject("rua", ruaRepository.findAll());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Bloco bloco, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(bloco);
		}
		try {
			blocoService.salvarBloco(bloco);

		} catch (NomeBlocoExistenteException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(bloco);
		}
		attributes.addFlashAttribute("mensagem", "Bloco Salvo!");
		return new ModelAndView("redirect:/bloco/novo");
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody List<Bloco>pesquisarPorCodigoGrupo(
			@RequestParam(name = "rua", defaultValue = "0") Long codigoRua){
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			
		}
		
		return blocoRepository.findByRuaCodigo(codigoRua);
		
	}
}


