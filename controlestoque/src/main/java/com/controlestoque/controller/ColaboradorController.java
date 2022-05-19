package com.controlestoque.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Enums.TipoIdentificacao;
import com.controlestoque.Repository.Colaboradores;
import com.controlestoque.Repository.filter.ColaboradorFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.model.Colaborador;
import com.controlestoque.service.ColaboradorService;
import com.controlestoque.service.exception.CpfCnpjIdJaCadastradaException;
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
		mv.addObject("tipoIdentificacao", TipoIdentificacao.values());
		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Colaborador colaborador, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(colaborador);
		}
		try {
			colService.salvar(colaborador);

		} catch (CpfCnpjIdJaCadastradaException e) {
			result.rejectValue("cpfCnpjId", e.getMessage(), e.getMessage());
			return novo(colaborador);
		}
		attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
		return new ModelAndView("redirect:/colaborador/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(ColaboradorFilter colaboradorFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("colaborador/pesquisaColaborador");
		mv.addObject("tipoIdentificacao", TipoIdentificacao.values());

		PageWrapper<Colaborador> paginaWrapper = new PageWrapper<>(colRepository.filtrar(colaboradorFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Colaborador> pesquisar(String nome) {

		validarTamanhoNome(nome);
		return colRepository.listpfCnpjId(nome);
	}

	@ExceptionHandler (IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
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

	private void validarTamanhoNome(String nome) {
		
		if (StringUtils.isEmpty(nome) || nome.length() < 0) {
			throw new IllegalArgumentException();
		}

	}

}