package com.controlestoque.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.controlestoque.Enums.StatusPedido;

public class PeriodoRelatorio {
	
	private Date dataInicial;
	private Date dataFim;
	private StatusPedido status;


	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	
	public boolean inicialMaior() {
		if(getDataInicial().before(getDataFim())){
			return false;
		}
		return true;
	}
}
