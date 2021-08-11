package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.FuncionarioRepository;
import com.controlestoque.model.Funcionario;
import com.controlestoque.model.Produto;

@Controller
public class FuncionarioController {
	
	@Autowired
	FuncionarioRepository funRepository;
	
	@GetMapping("/funcionario")
	public ModelAndView funcionario(){
		ModelAndView mv = new ModelAndView("funcionario/funcionario");
		mv.addObject("listFuncionario", funRepository.findAll());
		return mv;
	}
	
	@GetMapping("/inserir-funcionario")
	public ModelAndView insertFuncionario() {
		ModelAndView mv = new ModelAndView("funcionario/formFuncionario");
		mv.addObject("funcionario" , new Funcionario());
		return mv;
	}
	
	@PostMapping("/salvar-funcionario")
	public ModelAndView salvarFuncionario(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView();
		funRepository.save(funcionario);
		mv.setViewName( "redirect:/funcionario");
		return mv;
	}
	
	@GetMapping("/funcionario/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("funcionario/alterarFun");
		
		Funcionario fun = funRepository.getOne(id);
		mv.addObject("funcionario", fun);
		return mv;
		
	}
	@PostMapping("/alterar-funcionario")
	public ModelAndView alterarFuncionario(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView();
		funRepository.save(funcionario);
		mv.setViewName( "redirect:/funcionario");
		return mv;
	}
	
	@GetMapping("/excluir-funcionario/{idFuncionario}")
	public String excluirFuncionario(@PathVariable("idFuncionario") Long id) {
		funRepository.deleteById(id);
		return "redirect:/funcionario";
		
	}
	
	@GetMapping("FuncionarioNome")
	public ModelAndView listaNomeFuncionario() {
		ModelAndView mv = new ModelAndView("funcionario/pesquisaFuncionario");
		return mv;
	}
	
	@PostMapping("/pesquisa-funcionario")
	public ModelAndView pesquisaProduto(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("funcionario/pesquisaFuncionario");
		mv.addObject("funcionarioListResult", funRepository.findByNomeContainingIngnoreCase(nomepesquisa));
		mv.addObject("funcionario", new Funcionario());
		return mv;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
