package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Enums.TipoColaborador;
import com.controlestoque.Repository.Colaboradores;
import com.controlestoque.model.Colaborador;

@Controller
@RequestMapping("/colaborador")
public class ColaboradorController {
	
	@Autowired
	private Colaboradores colRepository;
	
	@RequestMapping("/novo")
	private ModelAndView novo(Colaborador colaborador) {
		ModelAndView mv = new ModelAndView("colaborador/novoColaborador");
		mv.addObject("tipoColaborador",TipoColaborador.values()) ;
		return mv;
	}

}
