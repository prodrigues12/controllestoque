package com.controlestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TesteLayout {
	
	@GetMapping("/")
	public ModelAndView layout() {
		ModelAndView mv = new ModelAndView("fragmentos/fragmentos");
		return mv;
		
	}

}
