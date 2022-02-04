package com.controlestoque.Enums;

import com.controlestoque.model.validation.gruop.*;


public enum TipoIdentificacao {

	ID("Magalu", "ID:", "000000", IdGroup.class) {
		public String formatar(String cpfCnpjId) {
			return cpfCnpjId.replaceAll("(\\d{6})", "$1");
		}

	},

	CPF("Terceirizado", "CPF:", "000.000.000-00", CpfGroup.class) {
		@Override
		public String formatar(String cpfCnpjId) {
			return cpfCnpjId.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	CNPJ("Filial", "CNPJ:", "00.000.000/0000-00", CnpjGroup.class) {
		@Override
		public String formatar(String cpfCnpjId) {
			return cpfCnpjId.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}

	};

	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> grupo;

	TipoIdentificacao(String descricao, String documento, String mascara, Class<?> grupo) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.grupo = grupo;
	}

	public abstract String formatar(String cpfCnpjId);

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}

	public Class<?> getGrupo() {
		return grupo;
	}

	public static String removerFormatacao(String cpfCnpjId) {
		return cpfCnpjId.replaceAll("\\.|-|/", "");
	}

}
