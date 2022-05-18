package com.controlestoque.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Repository.Grupos;
import com.controlestoque.Repository.Secoes;
import com.controlestoque.Repository.filter.GrupoFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Grupo;
import com.controlestoque.service.GrupoService;
import com.controlestoque.service.exception.NomeGrupoExistenteException;


@Controller
@RequestMapping("/grupo")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private Grupos gruRepository;
	
	@Autowired
	private Secoes secRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Grupo grupo) {
		ModelAndView mv = new ModelAndView("grupo/grupoNovo");
		mv.addObject("secao", secRepository.findAll());

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
	
	@GetMapping
	public ModelAndView pesquisarGrupo(GrupoFilter grupoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("grupo/pesquisaGrupo");
		mv.addObject("secao", secRepository.findAll());

		PageWrapper<Grupo> paginaWrapper = new PageWrapper<>(gruRepository.filtrar(grupoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	
}
