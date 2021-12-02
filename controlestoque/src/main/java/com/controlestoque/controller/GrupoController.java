package com.controlestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.model.Grupo;

@Controller
@RequestMapping("/grupo")
public class GrupoController {
	
	@RequestMapping("/novo")
	public ModelAndView novo(Grupo grupo) {
		ModelAndView mv = new ModelAndView("grupo/grupoNovo");
		
		return mv;
	}

}
