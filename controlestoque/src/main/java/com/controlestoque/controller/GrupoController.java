package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.GrupoRepository;
import com.controlestoque.model.Grupo;


@Controller
public class GrupoController {

	@Autowired
	GrupoRepository gr;
	
	
	@RequestMapping("/grupo")
	public ModelAndView listaGrupos(){
		ModelAndView mv = new ModelAndView("grupo/formGrupo");
		Iterable<Grupo> grupo = gr.findAll();
		mv.addObject("grupoList", grupo);
		return mv;
	}
	
	@RequestMapping(value="/grupo", method=RequestMethod.POST)
	public String form( Grupo grupo) {
		gr.save(grupo);
		return "redirect:/grupo";
	}
	
	@GetMapping("/alterarGrupo/{id}")
	public ModelAndView alterarGrupo(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("grupo/alterarGrupo");
		Grupo grupo = gr.getOne(id);
		mv.addObject("grupo", grupo);
		return mv;
		
	}
	
	@PostMapping("/alterarGrupo")
	public ModelAndView alterarGrupo(Grupo grupo) {
		ModelAndView mv = new ModelAndView();
		gr.save(grupo);
		mv.setViewName("redirect:/grupo");
		return mv;
	}
	
	@GetMapping("/excluirGrupo/{id}")
	public String excluirSecao(@PathVariable("id") Long id) {
		gr.deleteById(id);
		return "redirect:/grupo";
		
	}

	
	@PostMapping("**/pesquisa-grupo")
	public ModelAndView pesquisarSecao(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("grupo/pesquisaGrupo");
		mv.addObject("grupoListResult", gr.findByNomeContainingIngnoreCase(nomepesquisa));
		mv.addObject("grupo" , new Grupo());
		return mv;
	}
	
	@GetMapping("grupoNome")
	public ModelAndView listaNomeSecoes(){
		ModelAndView mv = new ModelAndView("secao/listaNome");
		Iterable<Grupo> grupo = gr.findAll();
		mv.addObject("grupoList", grupo);
		return mv;
	}

}
