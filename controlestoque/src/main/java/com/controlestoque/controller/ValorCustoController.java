package com.controlestoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Enums.TipoAjusteEstoque;
import com.controlestoque.Enums.UnidadeMedia;
import com.controlestoque.Repository.Produtos;

import com.controlestoque.model.ValorCusto;

import com.controlestoque.service.ValorCustoService;

@Controller
@RequestMapping("/valorCusto")
public class ValorCustoController {

	@Autowired
	private Produtos proRepository;

	@Autowired
	private ValorCustoService valService;

	@GetMapping("/ajuste/{codigo}")
	public ModelAndView editarEstoque(ValorCusto valorCusto, @PathVariable("codigo") Long codigo) {
		ModelAndView mv = new ModelAndView("valorCusto/ajusteValorCusto");

		mv.addObject("produto", proRepository.findByCodigo(codigo));
		mv.addObject("uniMedida", UnidadeMedia.values());
		mv.addObject("tipoAjuste", TipoAjusteEstoque.values());
		return mv;
	}

	@PostMapping("/ajuste/{codigo}")
	public ModelAndView novoEstoque(@RequestParam("codigo") Long codigo, @Valid ValorCusto valorCusto,
			BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return editarEstoque(valorCusto, codigo);

		} else {
			valService.atualizarValorCusto(valorCusto, codigo);
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
			return new ModelAndView("redirect:/valorCusto/ajuste/" + codigo);
		}
	}

}
