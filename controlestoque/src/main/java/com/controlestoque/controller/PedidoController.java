package com.controlestoque.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.controlestoque.Repository.FuncionarioRepository;
import com.controlestoque.Repository.ItemRepository;
import com.controlestoque.Repository.PedidoRepository;
import com.controlestoque.Repository.ProdutoRepository;
import com.controlestoque.model.Item;
import com.controlestoque.model.Pedido;
import com.controlestoque.model.Produto;
import com.controlestoque.model.Secao;
import com.controlestoque.service.PedidoService;


@Controller
public class PedidoController {
	
	@Autowired
	PedidoRepository pedRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	FuncionarioRepository funRepository;
	
	PedidoService pedService;
	
	
//	######## Usuario - PT-BR ##################
	@GetMapping("criar-pedido")
	public ModelAndView pedido() {
		ModelAndView mv = new ModelAndView("/pedido/criarPedido");
		mv.addObject("pedido" , new Pedido());
		return mv;
	}
	
	@PostMapping("salvar-pedido")
	public ModelAndView salvarPedido (Pedido pedido) {
		ModelAndView mv = new ModelAndView();
	
//			if(funRepository.tenhoCadastro(pedido.getFuncionario().getIdFuncionario())!= null) {
//				
//				
//			}
			pedido.setData(getDateTime());
			pedido.setStatus("NOVO");
			pedRepository.save(pedido);
			mv.setViewName("redirect:/lista-item");
			
	
		
		return mv;
	}
	
//	######## ADMIN  EN-US ##################
	@GetMapping("adicionar-pedido")
	public ModelAndView addPedido() {
		ModelAndView mv = new ModelAndView("/pedido/addPedido");
		mv.addObject("pedido" , new Pedido());
		return mv;
	}
	
	@PostMapping("save-pedido")
	public ModelAndView savePedido(Pedido pedido) {
		ModelAndView mv = new ModelAndView();
		pedido.setData(getDateTime());
		pedido.setStatus("NOVO");
		pedRepository.save(pedido);
		mv.setViewName("redirect:/lista-item");
		return mv;
	}
//	#########################################
	
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	@RequestMapping(value = "acompanhar-pedido", method = RequestMethod.GET)
	public ModelAndView acompanharPedido() {
		ModelAndView mv = new ModelAndView("/pedido/acompanharPedido");
		return mv;
	}
	
	
	@PostMapping("**/pesquisa-pedido")
	public ModelAndView pesquisaPeddido(@RequestParam("pedido") Long ped) {
		ModelAndView mv = new ModelAndView("pedido/acompanharPedido");
		mv.addObject("pedidoResult" , pedRepository.pesquisaPedido(ped));
		mv.addObject("pedido" , new Pedido());
		return mv;
	}
	
	@GetMapping("lista-pedidos")
	public ModelAndView listPedidos() {
		ModelAndView mv = new ModelAndView("pedido/listaPedidos");
		
		Iterable<Pedido> pedidoNovo = pedRepository.findAllNovo();
		mv.addObject("pedidoListNovo" , pedidoNovo);
		
		Iterable<Pedido> pedidoAnalise = pedRepository.findAllAnalise();
		mv.addObject("pedidoListAnalise" , pedidoAnalise);
		
		Iterable<Pedido> pedidoAberto = pedRepository.findAllAberto();
		mv.addObject("pedidoListAberto" , pedidoAberto);
		
		
		Iterable<Pedido> pedidoConcluido = pedRepository.findAllConcluido();
		mv.addObject("pedidoListConcluido" , pedidoConcluido);
		
		return mv;
	}
	
	@GetMapping("pedidos-concluidos")
	public ModelAndView pedidosConcluidos() {
		ModelAndView mv = new ModelAndView("pedido/pedidosConcluidos");
		
		Iterable<Pedido> pedidoNovo = pedRepository.findAllConcluido();
		mv.addObject("pedidoListConcluido" , pedidoNovo);
		
		return mv;
	}
	
	
	
	
}
