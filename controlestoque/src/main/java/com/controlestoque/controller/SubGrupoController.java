package com.controlestoque.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Repository.Grupos;
import com.controlestoque.Repository.SubGrupos;
import com.controlestoque.model.SubGrupo;
import com.controlestoque.service.SubGrupoService;
import com.controlestoque.service.exception.NomeSubGrupoExisteException;

@Controller
@RequestMapping("/subGrupo")
public class SubGrupoController implements Serializable {

	@Autowired
	private Grupos grupoRepository;

	@Autowired
	private SubGrupoService subGrupoService;

	private static final long serialVersionUID = 1L;

	@RequestMapping("/novo")
	public ModelAndView novo(SubGrupo subGrupo) {
		ModelAndView mv = new ModelAndView("subGrupo/subGrupoNovo");
		mv.addObject("grupo", grupoRepository.findAll());
		return mv;
	}

	@PatchMapping("/novo")
	public ModelAndView salvar(@Valid SubGrupo subGrupo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(subGrupo);
		}
		try {
			subGrupoService.salvar(subGrupo);

		} catch (NomeSubGrupoExisteException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(subGrupo);
		}
		attributes.addFlashAttribute("mensagem", "Sub-grupo Salvo!");
		return new ModelAndView("redirect:/subGrupo/novo");
	}

}
