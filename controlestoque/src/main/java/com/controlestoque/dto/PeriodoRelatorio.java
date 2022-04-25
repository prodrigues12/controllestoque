package com.controlestoque.dto;

import java.time.LocalDate;

public class PeriodoRelatorio {
	
	private LocalDate dataInicial;
	private LocalDate dataFim;

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataIncial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

}
