package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.FuncionarioRepository;
import com.controlestoque.model.Funcionario;

@Controller
public class FuncionarioController {
	
	@Autowired
	FuncionarioRepository funRepository;
	
	@GetMapping("funcionario")
	public ModelAndView funcionario(){
		ModelAndView mv = new ModelAndView("funcionario/funcionario");
		return mv;
	}
	
	@GetMapping("/inserir-funcionario")
	public ModelAndView insertFuncionario() {
		ModelAndView mv = new ModelAndView("funcionario/formFuncionario");
		return mv;
	}
	
	@PostMapping("/salvar-funcionario")
	public ModelAndView salvarFuncionario(Funcionario fun) {
		ModelAndView mv = new ModelAndView();
		funRepository.save(fun);
		mv.setViewName( "redirect:/funcionario");
		return mv;
	}
}
