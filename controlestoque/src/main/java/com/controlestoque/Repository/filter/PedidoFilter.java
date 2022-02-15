package com.controlestoque.Repository.filter;

import java.time.LocalDate;

import com.controlestoque.Enums.StatusPedido;
import com.controlestoque.Enums.Turno;

public class PedidoFilter {

	private Long codigo;
	private StatusPedido status;
	private Turno turno;
	private LocalDate dataInicio;

	private LocalDate dataFim;
	private String nomeColaborador;
	private String cpfCnpjId;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public String getCpfCnpjId() {
		return cpfCnpjId;
	}

	public void setCpfCnpjId(String cpfCnpjId) {
		this.cpfCnpjId = cpfCnpjId;
	}

}
