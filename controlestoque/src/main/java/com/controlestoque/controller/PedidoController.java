package com.controlestoque.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Enums.SetorMagalu;
import com.controlestoque.Enums.StatusPedido;
import com.controlestoque.Enums.Turno;
import com.controlestoque.Repository.Pedidos;
import com.controlestoque.Repository.Produtos;
import com.controlestoque.Repository.filter.PedidoFilter;
import com.controlestoque.controller.page.PageWrapper;
import com.controlestoque.controller.validator.PedidoValidator;
import com.controlestoque.dto.PedidosMes;
import com.controlestoque.model.ItemPedido;
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
	private Pedidos pedRepository;

	@Autowired
	private TabelaItemSession tabelaItens;

	@Autowired
	private PedidoService pedService;

	@Autowired
	private PedidoValidator pedidoValidator;

	@InitBinder("pedido")
	public void inicialiarValidador(WebDataBinder binder) {
		binder.setValidator(pedidoValidator);
	}

	@GetMapping("/novo")
	public ModelAndView novo(Pedido pedido) {
		ModelAndView mv = new ModelAndView("pedido/pedidoNovo");

		setUuid(pedido);

		mv.addObject("turno", Turno.values());
		mv.addObject("setorMagalu", SetorMagalu.values());
		mv.addObject("itens", pedido.getItens());
		return mv;
	}

	@GetMapping
	public ModelAndView pesquisar(PedidoFilter pedidoFilter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pedido/pesquisaPedido");
		objetosPedidos(mv);

		PageWrapper<Pedido> paginaWrapper = new PageWrapper<>(pedRepository.filtrar(pedidoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;

	}
<<<<<<< HEAD

	@GetMapping("/buscarPedido")
	public ModelAndView buscarPedido(PedidoFilter pedidoFilter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pedido/buscarPedido");
		PageWrapper<Pedido> paginaWrapper = new PageWrapper<>(pedRepository.buscarPedidos(pedidoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		mv.addObject("status", StatusPedido.values());
		return mv;

	}
=======
>>>>>>> parent of fdeec34 (informações sobre o pedido)

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Pedido pedido = pedRepository.buscarComItens(codigo);

		setUuid(pedido);

		for (ItemPedido item : pedido.getItens()) {
			tabelaItens.adicionarItem(pedido.getUuid(), item.getProduto(), item.getQuantidade());

		}

		ModelAndView mv = novo(pedido);
		mv.addObject(pedido);
		return mv;
	}

	@GetMapping("/pedidosNovos")
	public ModelAndView listaPedidosNovos(PedidoFilter pedidoFilter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pedido/listaPedidosPorStatus");
		objetosPedidos(mv);

		PageWrapper<Pedido> paginaWrapper = new PageWrapper<>(pedRepository.filtrarPedidosNovos(pedidoFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;

	}

	@GetMapping("/pedidosPendentes")
	public ModelAndView listaPedidosPendentes(PedidoFilter pedidoFilter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pedido/listaPedidosPorStatus");
		objetosPedidos(mv);

		PageWrapper<Pedido> paginaWrapper = new PageWrapper<>(
				pedRepository.filtrarPedidosPendentes(pedidoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@GetMapping("/pedidosSeparacao")
	public ModelAndView listaPedidosSeparacao(PedidoFilter pedidoFilter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pedido/listaPedidosPorStatus");
		objetosPedidos(mv);

		PageWrapper<Pedido> paginaWrapper = new PageWrapper<>(
				pedRepository.filtrarPedidosSeparacao(pedidoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@GetMapping("/pedidosCancelados")
	public ModelAndView listaPedidosCancelados(PedidoFilter pedidoFilter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pedido/listaPedidosPorStatus");
		objetosPedidos(mv);

		PageWrapper<Pedido> paginaWrapper = new PageWrapper<>(
				pedRepository.filtrarPedidosCancelados(pedidoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@GetMapping("/pedidosFinalizados")
	public ModelAndView listaPedidosFinalizados(PedidoFilter pedidoFilter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pedido/listaPedidosPorStatus");
		objetosPedidos(mv);

		PageWrapper<Pedido> paginaWrapper = new PageWrapper<>(
				pedRepository.filtrarPedidosFinalizados(pedidoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@GetMapping("/totalPorMes")
	public @ResponseBody List<PedidosMes> listarTotalPedidoPorMes() {
		return pedRepository.totalPorMes();
	}

	@PostMapping(value = "/novo", params = "novo")
	public ModelAndView salvar(Pedido pedido, BindingResult result, RedirectAttributes attributes) {

		validarPedido(pedido, result);

		if (result.hasErrors()) {
			return novo(pedido);
		}
		if (pedido.isPedidoNovo()) {
			pedService.salvar(pedido);
			attributes.addFlashAttribute("mensagem",
					String.format("Pedido nº %d salvo com sucesso!", pedido.getCodigo()));

			return new ModelAndView("redirect:/pedido/novo");
		}

		pedService.salvar(pedido);
		attributes.addFlashAttribute("mensagem",
				String.format("Pedido nº %d Alterado com sucesso!", pedido.getCodigo()));

		return new ModelAndView("redirect:/pedido/novo");
	}

	@PostMapping(value = "/novo", params = "pendente")
	public ModelAndView pendentePedido(Pedido pedido, BindingResult result, RedirectAttributes attributes) {
		validarPedido(pedido, result);

		if (result.hasErrors()) {
			return novo(pedido);
		}

		pedService.pendente(pedido);
		attributes.addFlashAttribute("mensagem",
				String.format("Pedido nº %d alterando para: Pendente", pedido.getCodigo()));

		return new ModelAndView("redirect:/pedido/" + pedido.getCodigo());
	}

	@PostMapping(value = "/novo", params = "cancelado")
	public ModelAndView canceladoPedido(Pedido pedido, BindingResult result, RedirectAttributes attributes) {
		validarPedido(pedido, result);

		if (result.hasErrors()) {
			return novo(pedido);
		}

		pedService.cancelar(pedido);
		attributes.addFlashAttribute("mensagem",
				String.format("Pedido nº %d alterando para: Cancelado", pedido.getCodigo()));

		return new ModelAndView("redirect:/pedido/" + pedido.getCodigo());
	}

	@PostMapping(value = "/novo", params = "separacao")
	public ModelAndView separacaoPedido(Pedido pedido, BindingResult result, RedirectAttributes attributes) {
		validarPedido(pedido, result);

		if (result.hasErrors()) {
			return novo(pedido);
		}

		pedService.emSeparacao(pedido);
		attributes.addFlashAttribute("mensagem",
				String.format("Pedido nº %d alterando para: Separação", pedido.getCodigo()));

		return new ModelAndView("redirect:/pedido/" + pedido.getCodigo());
	}

	@PostMapping(value = "/novo", params = "finalizar")
	public ModelAndView finlizarPedido(Pedido pedido, BindingResult result, RedirectAttributes attributes) {
		validarPedido(pedido, result);

		if (result.hasErrors()) {
			return novo(pedido);
		}

		pedService.finalizar(pedido);
		attributes.addFlashAttribute("mensagem",
				String.format("Pedido nº %d Finalizado com sucesso! ", pedido.getCodigo()));

		return new ModelAndView("redirect:/pedido/" + pedido.getCodigo());
	}

	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public ModelAndView adicionarItem(Long codigoProduto, String uuid) {
		Produto produto = proRepository.findByCodigo(codigoProduto);
		tabelaItens.adicionarItem(uuid, produto, 1);
		return mvTabelaPedido(uuid);
	}

	@PutMapping("/item/{codigoProduto}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoProduto") Produto produto, Integer quantidade,
			String uuid) {
		tabelaItens.alterarQuantidadeItem(uuid, produto, quantidade);
		return mvTabelaPedido(uuid);
	}

	@DeleteMapping("/item/{uuid}/{codigoProduto}")
	public ModelAndView excluirItem(@PathVariable("codigoProduto") Produto produto, @PathVariable String uuid) {
		tabelaItens.excluirItem(uuid, produto);
		return mvTabelaPedido(uuid);

	}

	@SuppressWarnings("deprecation")
	private void setUuid(Pedido pedido) {
		if (StringUtils.isEmpty(pedido.getUuid())) {
			pedido.setUuid(UUID.randomUUID().toString());
		}
	}

	private ModelAndView mvTabelaPedido(String uuid) {
		ModelAndView mv = new ModelAndView("pedido/tabelaItensPedido");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		return mv;
	}

	private void validarPedido(Pedido pedido, BindingResult result) {
		pedido.adicionarItens(tabelaItens.getItens(pedido.getUuid()));
		pedidoValidator.validate(pedido, result);

	}

	private void objetosPedidos(ModelAndView mv) {
		mv.addObject("turno", Turno.values());
		mv.addObject("status", StatusPedido.values());
	}

}
