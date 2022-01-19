package com.controlestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErroController {
	
	@GetMapping("/404")
	public String paginaNaoEncontrada() {
		return "404";
		
	}

}
