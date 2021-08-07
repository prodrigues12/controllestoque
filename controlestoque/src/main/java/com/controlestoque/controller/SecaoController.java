package com.controlestoque.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.SecaoRepository;
import com.controlestoque.model.Secao;


@Controller

public class SecaoController {
	
	@Autowired
	SecaoRepository sr;
	
	
	@GetMapping("/secao")
	public ModelAndView listaSecoes(){
		ModelAndView mv = new ModelAndView("secao/listSecao");
		Iterable<Secao> secao = sr.findAll();
		mv.addObject("secaoList", secao);
		return mv;
	}
	
	@GetMapping("insert-secao")
	public ModelAndView inserirSecao() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("secao" ,new Secao() );
		mv.setViewName("secao/formSecao");
		return mv;
				
	}
	
	@PostMapping("salvar-secao")
	public String form( Secao secao) {
		sr.save(secao);
		return "redirect:/insert-secao";
	}
	
	@GetMapping("/secao/{id}")
	public ModelAndView alterarSecao(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("secao/alterarSecao");
		Secao secao = sr.getOne(id);
		mv.addObject("secao", secao);
		return mv;
		
	}
	
	@PostMapping("/alterarSecao")
	public ModelAndView alterarSecao(Secao secao) {
		ModelAndView mv = new ModelAndView();
		sr.save(secao);
		mv.setViewName("redirect:/secao");
		return mv;
	}
	
	@GetMapping("/excluirSecao/{id}")
	public String excluirSecao(@PathVariable("id") Long id) {
		sr.deleteById(id);
		return "redirect:/secao";
		
	}

	
	@PostMapping("**/pesquisa-secao")
	public ModelAndView pesquisarSecao(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("secao/pesquisaSecao");
		mv.addObject("secaoListResult", sr.findByNomeContainingIngnoreCase(nomepesquisa));
		mv.addObject("secao" , new Secao());
		return mv;
	}
	
	
	
	@GetMapping("secaoNome")
	public ModelAndView listaNomeSecoes(){
		ModelAndView mv = new ModelAndView("secao/listaNome");
		Iterable<Secao> secao = sr.findAll();
		mv.addObject("secaoList", secao);
		return mv;
	}
}
	