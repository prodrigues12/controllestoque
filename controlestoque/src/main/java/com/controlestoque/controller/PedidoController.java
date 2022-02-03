package com.controlestoque.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Enums.Turno;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.model.Pedido;
import com.controlestoque.model.Produto;
import com.controlestoque.service.PedidoService;
import com.controlestoque.session.TabelaItemSession;


@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private Produtos proRepository;

	@Autowired
	private TabelaItemSession tabalaItens;
	
	@Autowired
	private PedidoService pedService;

	@GetMapping("/novo")
	public ModelAndView novo(Pedido pedido) {
		ModelAndView mv = new ModelAndView("pedido/pedidoNovo");
		pedido.setUuid(UUID.randomUUID().toString());
		mv.addObject("turno", Turno.values());
		mv.addObject("itens",pedido.getItens());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(Pedido pedido , RedirectAttributes attributes, BindingResult result) {
		

			pedido.adicionarItens(tabalaItens.getItens(pedido.getUuid()));
			pedService.salvar(pedido);
			attributes.addFlashAttribute("mensagem", "Pedido Salvo com sucesso");
			return new ModelAndView("redirect:/pedido/novo");
	}

	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoProduto, String uuid) {
		
		Produto produto = proRepository.findByCodigo(codigoProduto);
		tabalaItens.adicionarItem(uuid,produto,1);
		
		return mvTabelaPedido(uuid);
	}
	
	@PutMapping("/item/{codigo}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoProduto") Produto  produto, 
			Integer quantidade, String uuid) {
	
		tabalaItens.alterarQauntidadeItem(uuid, produto, quantidade);
		return mvTabelaPedido(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoProduto}")
	public ModelAndView excluirItem(@PathVariable("codigoProduto") Produto  produto, 
			@PathVariable String uuid) {
		tabalaItens.excluirItem(uuid, produto);
		return mvTabelaPedido(uuid);
		
		
	}

	private ModelAndView mvTabelaPedido(String uuid) {
		ModelAndView mv = new ModelAndView("pedido/tabelaItensPedido");
		mv.addObject("itens", tabalaItens.getItens(uuid));
		return mv;
	}
}
