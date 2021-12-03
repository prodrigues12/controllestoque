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

import com.controlestoque.model.Grupo;
import com.controlestoque.service.GrupoService;
import com.controlestoque.service.exception.NomeGrupoExistenteException;


@Controller
@RequestMapping("/grupo")
public class GrupoController {

	@Autowired
	GrupoService grupoService;

	@RequestMapping("/novo")
	public ModelAndView novo(Grupo grupo) {
		ModelAndView mv = new ModelAndView("grupo/grupoNovo");

		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Grupo grupo, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(grupo);
		}

		try {
			grupoService.salvarGrupo(grupo);

		} catch (NomeGrupoExistenteException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(grupo);
		}

		attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
		return new ModelAndView("redirect:/grupo/novo");
	}

//	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
//	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Grupo grupo, BindingResult result) {
//		if (result.hasErrors()) {
//			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
//		}
//
//		grupo = grupoService.salvarGrupo(grupo);
//
//		return ResponseEntity.ok(grupo);
//	}

}
