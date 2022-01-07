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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Repository.Blocos;
import com.controlestoque.Repository.Ruas;
import com.controlestoque.Repository.filter.BlocoFilter;
import com.controlestoque.Repository.filter.RuaFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Bloco;
import com.controlestoque.model.Rua;
import com.controlestoque.service.BlocoService;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;
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

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
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
	public @ResponseBody List<Bloco> pesquisarPorCodigoRua(
			@RequestParam(name = "rua", defaultValue = "0") Long codigoRua) {
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {

		}

		return blocoRepository.findByRuaCodigo(codigoRua);

	}

	@GetMapping
	public ModelAndView pesquisar(BlocoFilter blocoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("bloco/pesquisaBloco");
		mv.addObject("rua", ruaRepository.findAll());
		
		PageWrapper<Bloco> paginaWrapper = new PageWrapper<>(blocoRepository.filtrar(blocoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;

	}

	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Bloco bloco) {
		try {
			blocoService.excluir(bloco);

		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Bloco bloco) {
		ModelAndView mv = novo(bloco);
		mv.addObject(bloco);
		mv.addObject(mv);
		return mv;
	}
}
