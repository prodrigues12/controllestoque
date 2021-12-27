package com.controlestoque.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Repository.Grupos;
import com.controlestoque.Repository.Subgrupos;
import com.controlestoque.Repository.filter.SubgrupoFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Subgrupo;
import com.controlestoque.service.SubGrupoService;
import com.controlestoque.service.exception.NomeSubGrupoExisteException;

@Controller
@RequestMapping("/subgrupo")
public class SubgrupoController implements Serializable {

	@Autowired
	private Grupos grupoRepository;

	@Autowired
	private SubGrupoService subGrupoService;

	@Autowired
	private Subgrupos subGrupoRepository;

	private static final long serialVersionUID = 1L;

	@RequestMapping("/novo")
	public ModelAndView novo(Subgrupo subGrupo) {
		ModelAndView mv = new ModelAndView("subGrupo/subGrupoNovo");
		mv.addObject("grupo", grupoRepository.findAll());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Subgrupo subGrupo, BindingResult result, RedirectAttributes attributes) {
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
		return new ModelAndView("redirect:/subgrupo/novo");
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody List<Subgrupo>pesquisarPorCodigoGrupo(
			@RequestParam(name = "grupo", defaultValue = "0") Long condigoGrupo){
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			
		}
		
		return subGrupoRepository.findByGrupoCodigo(condigoGrupo);
		
	}

	@GetMapping
	public ModelAndView pesquisarSubgrupo(SubgrupoFilter subgrupoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable,  HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("subgrupo/pesquisaSubgrupo");
		mv.addObject("grupo", grupoRepository.findAll());
		
		PageWrapper<Subgrupo> paginaWrapper = new PageWrapper<>(subGrupoRepository.filtrar(subgrupoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
		
	}
	
}
	
	


