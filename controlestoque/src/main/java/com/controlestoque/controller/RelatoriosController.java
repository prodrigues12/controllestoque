package com.controlestoque.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.Enums.StatusPedido;
import com.controlestoque.Repository.Secoes;
import com.controlestoque.dto.PeriodoRelatorio;
import com.controlestoque.model.Secao;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private Secoes secaoRepository;;
	
	@GetMapping()
	public ModelAndView relatorio(PeriodoRelatorio periodoRelatorio) {
		ModelAndView mv = new ModelAndView("relatorios/relatorios");
		return mv;
	}

	@GetMapping("/pedidosFinalizados")
	public ModelAndView reltoriosPedidosFinalizados(PeriodoRelatorio periodoRelatorio) {
		ModelAndView mv = new ModelAndView("relatorios/relatoriosPedidosFinalizados");
		mv.addObject("status", StatusPedido.values());
		return mv;

	}
	@GetMapping("/pedidosCompleto")
	public ModelAndView reltoriosPedidos(PeriodoRelatorio periodoRelatorio) {
		ModelAndView mv = new ModelAndView("relatorios/relatoriosPedidos");
		mv.addObject("status", StatusPedido.values());
		return mv;

	}
	
	@GetMapping("/estoque")
	public ModelAndView reltorioEstoque(PeriodoRelatorio periodoRelatorio) {
		ModelAndView mv = new ModelAndView("relatorios/estoque");
		return mv;
	
	}
	
	@GetMapping("/estoque_movimentacao")
	public ModelAndView reltorioEstoqueMovimetacao(PeriodoRelatorio periodoRelatorio) {
		ModelAndView mv = new ModelAndView("relatorios/estoqueMovimentacao");
		return mv;
	}
	
	@GetMapping("/inventario")
	public ModelAndView reltoriosInventario(Secao secao) {
		ModelAndView mv = new ModelAndView("relatorios/relatorioInventario");
		mv.addObject("secao", secaoRepository.findAll());
		return mv;

	}
	
	
	@PostMapping("/pedidosFinalizados")
	public ResponseEntity<Object> pedidosStatusPeriodo(@RequestParam Map<String, Object> parametros,
			HttpServletResponse response, PeriodoRelatorio periodoRelatorio) throws Exception {

		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		
		parametros.put("data_inicio",periodoRelatorio.getDataInicial());
		parametros.put("data_fim",periodoRelatorio.getDataFim());	
		parametros.put("status", periodoRelatorio.getStatus().toString());
		
		
		JasperPrint empReport = JasperFillManager.fillReport(
				JasperCompileManager.compileReport(ResourceUtils
						.getFile("classpath:relatorios/relatorio_pedidosStatus.jrxml").getAbsolutePath()),
				parametros, dataSource.getConnection());

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "relatorio_pedidosStatus.pdf");

		return new ResponseEntity<Object>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

	}
	
	
	@PostMapping("/pedidosCompleto")
	public ResponseEntity<Object> pedidosCompleto(@RequestParam Map<String, Object> parametros,
			HttpServletResponse response, PeriodoRelatorio periodoRelatorio) throws Exception {

		
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		
		parametros.put("data_inicio",periodoRelatorio.getDataInicial());
		parametros.put("data_fim",periodoRelatorio.getDataFim());	
		
	
		JasperPrint empReport = JasperFillManager.fillReport(
				JasperCompileManager.compileReport(ResourceUtils
						.getFile("classpath:relatorios/relatorio_pedidoFinalizado.jrxml").getAbsolutePath()),
				parametros, dataSource.getConnection());

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "relatorio_pedidoFinalizado.pdf");

		return new ResponseEntity<Object>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

	}
	
	@PostMapping("/inventario")
	public ResponseEntity<Object> relatorioInventario(@RequestParam Map<String, Object> parametros,
			HttpServletResponse response, Secao secao) throws Exception {

		
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		
		parametros.put("Seção", secao.getCodigo());
		
	
		JasperPrint empReport = JasperFillManager.fillReport(
				JasperCompileManager.compileReport(ResourceUtils
						.getFile("classpath:relatorios/relatorio_inventario_contagem.jrxml").getAbsolutePath()),
				parametros, dataSource.getConnection());

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "relatorio_inventario_contagem.pdf");

		return new ResponseEntity<Object>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

	}

}
