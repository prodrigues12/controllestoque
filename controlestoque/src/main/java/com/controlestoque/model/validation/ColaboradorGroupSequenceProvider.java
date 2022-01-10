package com.controlestoque.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.controlestoque.model.Colaborador;

public class ColaboradorGroupSequenceProvider  implements DefaultGroupSequenceProvider<Colaborador>{

	@Override
	public List<Class<?>> getValidationGroups(Colaborador colaborador) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Colaborador.class);
		
		if(isColaboradorSelecionado(colaborador)) {
			grupos.add(colaborador.getTipoIdentificacao().getGrupo());
		}
	
		
		return grupos;
	}

	private boolean isColaboradorSelecionado(Colaborador colaborador) {
		// TODO Auto-generated method stub
		return colaborador != null && colaborador.getTipoIdentificacao() != null;
	}

}
