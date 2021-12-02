package com.controlestoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Repository.SecaoRepository;
import com.controlestoque.model.Secao;
import com.controlestoque.service.SecaoService;
import com.controlestoque.service.exception.NomeSecaoExistenteException;

@Controller
@RequestMapping("/secao")
public class SecaoController {

	@Autowired
	SecaoRepository secRepository;

	@Autowired
	SecaoService secService;

	@RequestMapping("/novo")
	public ModelAndView novo(Secao secao) {
		ModelAndView mv = new ModelAndView("secao/secaoNovo");
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Secao secao, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(secao);
		}
		
		try {
			secService.salvarSecao(secao);
			
		} catch (NomeSecaoExistenteException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(secao);
		}

			attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
			return new ModelAndView("redirect:/secao/novo");
		}
	

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Secao secao, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}

		secao = secService.salvarSecao(secao);

		return ResponseEntity.ok(secao);
	}

}
