package com.controlestoque.dto;

import java.math.BigDecimal;

public class ValorCustoTurnoDTO {

	
	private String turno;
	private BigDecimal valor;
	
	public ValorCustoTurnoDTO() {
		
	}
	
	public ValorCustoTurnoDTO(String turno, BigDecimal valor) {
		super();
		this.turno = turno;
		this.valor = valor;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
