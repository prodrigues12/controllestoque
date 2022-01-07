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

import com.controlestoque.Repository.Ruas;
import com.controlestoque.Repository.filter.RuaFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Rua;
import com.controlestoque.service.RuaService;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;
import com.controlestoque.service.exception.NomeRuaExistenteException;

@Controller
@RequestMapping("/rua")
public class RuaController {

	@Autowired
	private Ruas ruaRepository;

	@Autowired
	RuaService ruaService;

	@RequestMapping("/novo")
	public ModelAndView novo(Rua rua) {
		ModelAndView mv = new ModelAndView("rua/ruaNovo");

		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Rua rua, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(rua);
		}

		try {
			ruaService.salvarRua(rua);
			

		} catch (NomeRuaExistenteException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(rua);
		}

		attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
		return new ModelAndView("redirect:/rua/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(RuaFilter ruaFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("rua/pesquisaRua");

		PageWrapper<Rua> paginaWrapper = new PageWrapper<>(ruaRepository.filtrar(ruaFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;

	}

	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?>excluir(@PathVariable("codigo")Rua rua ){
		try {
			ruaService.excluir(rua);
		
		}catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Rua rua) {
		ModelAndView mv = novo(rua);
		mv.addObject(rua);
		mv.addObject(mv);
		return mv;
	}

	
	
	
	
	
	
}
