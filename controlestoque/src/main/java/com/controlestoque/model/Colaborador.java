package com.controlestoque.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;


import com.controlestoque.Enums.TipoSolicitante;
import com.controlestoque.model.validation.IdMagalu;
import com.controlestoque.model.validation.gruop.CnpjGroup;
import com.controlestoque.model.validation.gruop.CpfGroup;
import com.controlestoque.model.validation.gruop.IdGroup;

@Entity
public class Colaborador implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "Campo nome é obrigatório")
	private String nome;
	

	@NotNull(message = "Empresa é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa")
	private TipoSolicitante tipoSolicitante;;
	
	@NotBlank(message = "CPF/CNPJ é obrigatório")
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	@IdMagalu(groups = IdGroup.class)
	private String cpfCnpjId;
	

	@Email(message = "E-mail inválido")
	private String email;
	
	@PrePersist
	@PreUpdate
	private void prePersistPreUpdate() {
		this.cpfCnpjId = TipoSolicitante.removerFormatacao(this.cpfCnpjId);
	}
	
	@PostLoad
	private void postLoad() {
		this.cpfCnpjId = this.tipoSolicitante.formatar(this.cpfCnpjId);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public TipoSolicitante getTipoSolicitante() {
		return tipoSolicitante;
	}

	public void setTipoSolicitante(TipoSolicitante tipoSolicitante) {
		this.tipoSolicitante = tipoSolicitante;
	}

	public String getCpfCnpjId() {
		return cpfCnpjId;
	}

	public void setCpfCnpjId(String cpfCnpjId) {
		this.cpfCnpjId = cpfCnpjId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isColaboradorNovo() {
		return codigo == null;
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
		Colaborador other = (Colaborador) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public String getCpfCnpjIdSemFormatacao() {
		
		return TipoSolicitante.removerFormatacao(cpfCnpjId);
	}
	


}
