package com.controlestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Repository.ItemRepository;
import com.controlestoque.Repository.PedidoRepository;
import com.controlestoque.Repository.ProdutoRepository;
import com.controlestoque.model.Item;
import com.controlestoque.model.Pedido;
import com.controlestoque.model.Produto;

@Controller
public class ItemController {
	
	@Autowired
	ItemRepository iteRepository;
	
	@Autowired
	ProdutoRepository prodRepository;
	
	@Autowired
	PedidoRepository pedRepository;
	
	
	@RequestMapping("/lista-item")
	public ModelAndView pedido() {
		ModelAndView mv = new ModelAndView("/item/formItem");
		
		Iterable<Produto> produto = prodRepository.findAll();
		mv.addObject("produtoList", produto);
		
//		Iterable<Item> prod = prodRepository.l;
//		mv.addObject("produtoList", prod);
	
		
		pedRepository.lastIdPedido();
		Iterable<Item> item = iteRepository.ListProduto();
		mv.addObject("itemList", item);
		return mv;
	}
	
	@RequestMapping(value="/salvar-item", method=RequestMethod.POST)
	public String form(Item item) {
		Pedido ped =new Pedido();
		ped.setIdPedido(pedRepository.lastIdPedido());
		item.setPedido(ped);
		iteRepository.save(item);
		return "redirect:/lista-item";
	}
	@GetMapping("/excluirItem/{id}")
	public String excluirItem(@PathVariable("id") Long id) {
		iteRepository.deleteById(id);
		return "redirect:/lista-item";
	}
}
