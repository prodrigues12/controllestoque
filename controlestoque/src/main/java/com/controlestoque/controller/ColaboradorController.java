package com.controlestoque.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Enums.TipoSolicitante;
import com.controlestoque.Repository.Colaboradores;
import com.controlestoque.Repository.filter.ColaboradorFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Colaborador;
import com.controlestoque.service.ColaboradorService;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/colaborador")
public class ColaboradorController {

	@Autowired
	private Colaboradores colRepository;

	@Autowired
	private ColaboradorService colService;

	@RequestMapping("/novo")
	private ModelAndView novo(Colaborador colaborador) {
		ModelAndView mv = new ModelAndView("colaborador/novoColaborador");
		mv.addObject("tipoSolicitante", TipoSolicitante.values());
		return mv;
	}

	@RequestMapping(value = {"/novo", "{\\d+}"} , method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Colaborador colaborador, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(colaborador);
		} else {
			colService.salvar(colaborador);
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
			return new ModelAndView("redirect:/colaborador/novo");
		}
	}

	@GetMapping
	public ModelAndView pesquisar(ColaboradorFilter colaboradorFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("colaborador/pesquisaColaborador");
//		mv.addObject("colaborador", colRepository.findAll());
//		mv.addObject("tipoSolicitante", TipoSolicitante.values());

		PageWrapper<Colaborador> paginaWrapper = new PageWrapper<>(colRepository.filtrar(colaboradorFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Colaborador colaborador) {
		try {
			colService.excluir(colaborador);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Colaborador colaborador) {
		ModelAndView mv = novo(colaborador);
		mv.addObject(colaborador);
		mv.addObject(mv);
		return mv;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
