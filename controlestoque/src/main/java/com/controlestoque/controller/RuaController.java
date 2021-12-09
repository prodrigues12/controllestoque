package com.controlestoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.model.Rua;
import com.controlestoque.service.RuaService;
import com.controlestoque.service.exception.NomeBlocoExistenteException;

@Controller
@RequestMapping("/rua")
public class RuaController {
	
	@Autowired
	RuaService ruaService;

		@RequestMapping("/novo")
		public ModelAndView novo(Rua rua) {
			ModelAndView mv = new ModelAndView("rua/ruaNovo");

			return mv;
		}

		@RequestMapping(value = "/novo", method = RequestMethod.POST)
		public ModelAndView salvar(@Valid Rua rua, BindingResult result, RedirectAttributes attributes) {

			if (result.hasErrors()) {
				return novo(rua);
			}

			try {
				ruaService.salvarRua(rua);

			} catch (NomeBlocoExistenteException e) {
				result.rejectValue("nome", e.getMessage(), e.getMessage());
				return novo(rua);
			}

			attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
			return new ModelAndView("redirect:/bloco/novo");
		}

	}



