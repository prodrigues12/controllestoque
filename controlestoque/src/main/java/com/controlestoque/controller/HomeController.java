package com.controlestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		return mv;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/login");
		return mv;
	}
	
	@GetMapping("/pedido")
	public ModelAndView pedido() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pedido/formPedido");
		return mv;
		
	}@GetMapping("/admin")
	public ModelAndView admController() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/admin");
		return mv;
	}
	
	
}
