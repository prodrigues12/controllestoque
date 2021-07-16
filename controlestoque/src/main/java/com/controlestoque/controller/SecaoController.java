package com.controlestoque.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	
	@RequestMapping("/secao")
	public ModelAndView listaSecoes(){
		ModelAndView mv = new ModelAndView("secao/formSecao");
		Iterable<Secao> secao = sr.findAll();
		mv.addObject("secaoList", secao);
		return mv;
	}
	
	@RequestMapping(value="/secao", method=RequestMethod.POST)
	public String form( Secao secao) {
		sr.save(secao);
		return "redirect:/secao";
	}
	
	@GetMapping("/alterarSecao/{id}")
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
	