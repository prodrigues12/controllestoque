package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.PedidoRepository;
import com.controlestoque.model.Pedido;


@Controller
public class HomeController {
	
	@Autowired
	PedidoRepository pedRepository;
	
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
		
	}
//	
	
}
