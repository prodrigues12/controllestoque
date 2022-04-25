package com.controlestoque.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlestoque.dto.PeriodoRelatorio;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {

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
	public ResponseEntity<PeriodoRelatorio> getEmployeeRecordReport() {

		try {
			
		;

			// dynamic parameters required for report
			Map<String, Object> empParams = new HashMap<String, Object>();
			empParams.put("data_inicial" , "01-02-2022");
			empParams.put("data_final"  , "25-04-2022");
			JasperPrint empReport = JasperFillManager.fillReport(JasperCompileManager
					.compileReport(ResourceUtils.getFile("classpath:relatorios/relatorio_pedidosMes.jrxml").getAbsolutePath())																					
					, empParams
					, new JREmptyDataSource());

			HttpHeaders headers = new HttpHeaders();
			
			// set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "relatorio_pedidosMes.pdf");
			
			// create the report in PDF format
			return new ResponseEntity<PeriodoRelatorio>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
