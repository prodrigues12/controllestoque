package com.controlestoque.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.controlestoque.Enums.SetorMagalu;
import com.controlestoque.Enums.StatusPedido;
import com.controlestoque.Enums.Turno;
import com.controlestoque.dto.PedidosMes;

@Entity
@DynamicUpdate

@SqlResultSetMapping(name = "mappingPedidos", classes = @ConstructorResult(targetClass = PedidosMes.class, columns = {
		@ColumnResult(name = "mes", type = String.class), @ColumnResult(name = "total", type = Integer.class) }))

public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "data_criacao")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataCriacao;

	@Column(name = "data_modificacao")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataModificacao;

	private String observacao;

	@ManyToOne
	@JoinColumn(name = "codigo_colaborador")
	private Colaborador colaborador;

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.NOVO;

	@Enumerated(EnumType.STRING)
	private Turno turno;

	@Enumerated(EnumType.STRING)
	private SetorMagalu setorMagalu;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPedido> itens = new ArrayList<>();

	@Transient
	private String uuid;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModifucacao(LocalDate dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public SetorMagalu getSetorMagalu() {
		return setorMagalu;
	}

	public void setSetorMagalu(SetorMagalu setorMagalu) {
		this.setorMagalu = setorMagalu;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isPedidoNovo() {
		return codigo == null;
	}
	
	public boolean isPedidoFinalizado() {
		return status == StatusPedido.FINALIZADO;
	}

	public void adicionarItens(List<ItemPedido> itens) {

		this.itens = itens;
		this.itens.forEach(i -> i.setPedido(this));

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}