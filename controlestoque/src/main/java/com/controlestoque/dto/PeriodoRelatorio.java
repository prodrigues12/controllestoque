package com.controlestoque.dto;

import java.time.LocalDate;

import com.controlestoque.Enums.StatusPedido;

public class PeriodoRelatorio {
	
	private String dataInicial;
	private String dataFim;
	private StatusPedido status;


	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}
}
