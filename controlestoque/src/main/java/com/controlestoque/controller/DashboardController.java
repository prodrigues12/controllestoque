package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.Pedidos;

@Controller
public class DashboardController {
	
	@Autowired
	private Pedidos pedRepository;
	
	@GetMapping("/")
	public ModelAndView layout() {
		ModelAndView mv = new ModelAndView("dashboard");
		mv.addObject("pedidosNovos", pedRepository.statusIgualNovo());
		mv.addObject("pedidosEspera", pedRepository.statusIgualEspera());
		mv.addObject("pedidosCancelados", pedRepository.statusIgualCancelado());
		mv.addObject("pedidosFinalizados", pedRepository.statusIgualFinalizado());
		return mv;
		
	}

	@GetMapping("/menu")
	public ModelAndView menu() {
		ModelAndView mv = new ModelAndView("fragmentos/fragmentos2");
		return mv;
		
	}
}
