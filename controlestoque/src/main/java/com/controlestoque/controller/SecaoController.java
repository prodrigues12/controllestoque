package com.controlestoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Repository.SecaoRepository;
import com.controlestoque.model.Secao;

@Controller
@RequestMapping("/secao")
public class SecaoController {

	
	@Autowired
	SecaoRepository secRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Secao secao) {
		ModelAndView mv = new ModelAndView("secao/secaoNovo");
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Secao secao, BindingResult result , RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(secao);
		} else {
			secRepository.save(secao);
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
			return new ModelAndView("redirect:/secao/novo");
		}
		
		
		
		
	}
}
