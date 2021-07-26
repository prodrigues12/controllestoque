package com.controlestoque.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	PedidoService pedService;
	
	@RequestMapping(value = "novo-pedido", method = RequestMethod.GET)
	public ModelAndView pedido() {
		ModelAndView mv = new ModelAndView("/pedido/formPedido");
		return mv;
	}
	
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	@RequestMapping(value = "novo-pedido", method = RequestMethod.POST)
	public String savePedido(Pedido pedido) {
		pedido.setData(getDateTime());
		pedido.setStatus("NOVO");
		pedRepository.save(pedido);
		return "redirect:/lista-item";
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
	
//	@PostMapping("**/pesquisa-pedido")
//	public ModelAndView pesquisarSecao(@RequestParam("nomepesquisa") Long ped) {
//		ModelAndView mv = new ModelAndView("pedido/acomapnharPedido");
//		mv.addObject("pedidoListResult",pedRepository.pesquisaPedido(ped) );
//		mv.addObject("pedido" , new Pedido());
//		return mv;
//	}

}
