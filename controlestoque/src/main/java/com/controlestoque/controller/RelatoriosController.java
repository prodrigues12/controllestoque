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

import com.controlestoque.dto.PeriodoRelatorio;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@Autowired
	private DataSource dataSource;

//	@GetMapping("/pedidosFinalizados")
	public ModelAndView reltoriosPedidosFinalizados() {
		ModelAndView mv = new ModelAndView("relatorios/relatoriosPedidosFinalizados");
		mv.addObject(new PeriodoRelatorio());

		return mv;

	}

	@PostMapping("/pedidosFinalizados")
	public ModelAndView reltoriosPedidosFinalizados(PeriodoRelatorio periodoRelatorio) {

		Map<String, Object> parametros = new HashMap<>();

//		Date dataIncial = 
//		Date dataFinal =  periodoRelatorio.getDataInicial()
//		parametros.put("formaat", "pdf");
//		parametros.put("data_inicial", "2022-02-01");
//		parametros.put("data_final", "2022-04-25");

		return new ModelAndView("relatorio_pedidosMes", parametros);

	}


	@GetMapping("/pedidosFinalizados")
	public ResponseEntity<Object> getEmployeeRecordReport(@RequestParam Map<String, Object> parametros, HttpServletResponse response) {

		try {
			
//			Map<String, Object> paramentros = new HashMap<String, Object>();
			
			parametros = parametros == null ? parametros = new HashMap<>() : parametros;
			
			parametros.put("status" , "NOVO");

			
			JasperPrint empReport = JasperFillManager.fillReport(JasperCompileManager
					.compileReport(ResourceUtils.getFile("classpath:relatorios/relatorio_pedidosFinalizadosSem.jrxml").getAbsolutePath())																					
					, parametros
					, dataSource.getConnection());

//			##############
			HttpHeaders headers = new HttpHeaders();
			
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "relatorio_pedidosMes.pdf");
			
			
			return new ResponseEntity<Object>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	

}
