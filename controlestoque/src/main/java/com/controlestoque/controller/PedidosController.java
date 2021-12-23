package com.controlestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pedido")
public class PedidosController {
	
	@GetMapping
	public ModelAndView pedidos() {
		ModelAndView mv= new ModelAndView("pedido/pedidoNovo");
		return mv;
	}

	
	
}
