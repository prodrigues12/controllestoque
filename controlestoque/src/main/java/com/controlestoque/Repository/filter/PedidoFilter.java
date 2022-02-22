package com.controlestoque.Repository.filter;

import java.time.LocalDate;



import com.controlestoque.Enums.StatusPedido;
import com.controlestoque.Enums.Turno;

public class PedidoFilter {

	private Long codigo;
	private StatusPedido status;
	private Turno turno;
	private String desde;
	private String ate;
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


	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getAte() {
		return ate;
	}

	public void setAte(String ate) {
		this.ate = ate;
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
