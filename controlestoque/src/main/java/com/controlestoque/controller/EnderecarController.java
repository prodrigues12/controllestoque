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

import com.controlestoque.Repository.Enderecamentos;
import com.controlestoque.Repository.Enderecos;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.Repository.filter.EnderecoFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Enderecar;
import com.controlestoque.model.Endereco;
import com.controlestoque.model.Secao;
import com.controlestoque.service.EnderecarService;
import com.controlestoque.service.EnderecoService;
import com.controlestoque.service.exception.EnderecoJaCadastradoException;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;
import com.controlestoque.service.exception.enderecoOcupadoException;

@Controller
@RequestMapping("/enderecar")
public class EnderecarController {

	@Autowired
	private EnderecarService enderecarService;

	@Autowired
	private Enderecamentos enderecarRepository;
	@Autowired
	private Produtos proRepository;

	@Autowired
	private Enderecos endRepository;
	
	

	@RequestMapping("/novo")
	public ModelAndView novo(Enderecar enderecar) {
		ModelAndView mv = new ModelAndView("enderecar/novoEnderecar");
		mv.addObject("produto", proRepository.findAll());
		mv.addObject("endereco", endRepository.findAll());
		return mv;
	}



	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvarEnderecamento(@Valid Enderecar enderecar, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(enderecar);
		}
		try {

			enderecarService.salvandoEndereco(enderecar);

		} catch (enderecoOcupadoException e) {

			result.rejectValue("Endereco", e.getMessage(), e.getMessage());
			return novo(enderecar);
		}

		attributes.addFlashAttribute("mensagem", "Enderecamento realizado com sucesso!");
		return new ModelAndView("redirect:/enderecar/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(Enderecar enderecar, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("enderecar/pesquisarEnderecamento");
		mv.addObject("produto", proRepository.findAll());

		PageWrapper<Enderecar> paginaWrapper = new PageWrapper<>(enderecarRepository.filtrar(enderecar, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@GetMapping("/{codigo}")
	public ModelAndView editarEndereco(@PathVariable("codigo") Enderecar enderecar) {
		ModelAndView mv = novo(enderecar);
		mv.addObject(enderecar);
		return mv;

	}
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Enderecar enderecar) {
		try {
			enderecarService.excluir(enderecar);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.ok().build();
	}
	

}
