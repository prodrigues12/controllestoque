package com.controlestoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.ItemRepository;
import com.controlestoque.Repository.PedidoRepository;
import com.controlestoque.Repository.ProdutoRepository;
import com.controlestoque.model.Item;
import com.controlestoque.model.Pedido;
import com.controlestoque.model.Produto;
import com.controlestoque.model.Secao;

@Controller
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ProdutoRepository prodRepository;
	
	@Autowired
	PedidoRepository pedRepository;
	
	
	@RequestMapping("/lista-item")
	public ModelAndView pedido() {
		ModelAndView mv = new ModelAndView("/item/formItem");
		
		Iterable<Produto> produto = prodRepository.findAll();
		mv.addObject("produtoList", produto);
		
	
		pedRepository.lastIdPedido();
		Iterable<Item> item = itemRepository.ListProduto(pedRepository.lastIdPedido());
		mv.addObject("itemList", item);
		return mv;
	}
	
	@RequestMapping(value="/lista-item", method=RequestMethod.POST)
	public String form(Item item) {
		Pedido ped =new Pedido();
		ped.setIdPedido(pedRepository.lastIdPedido());
		item.setPedido(ped);
		itemRepository.save(item);
		return "redirect:/lista-item";
	}
}
