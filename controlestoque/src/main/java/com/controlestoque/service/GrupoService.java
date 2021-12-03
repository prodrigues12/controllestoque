package com.controlestoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Grupos;
import com.controlestoque.model.Grupo;
import com.controlestoque.service.exception.NomeGrupoExistenteException;


@Service
public class GrupoService {

	@Autowired
	Grupos grupoRepository;
	
	public Grupo salvarGrupo(Grupo grupo) {
		Optional<Grupo> grupoOptional = grupoRepository.findByNomeIgnoreCase(grupo.getNome());
		
		if(grupoOptional.isPresent()) {
			throw new NomeGrupoExistenteException("Nome: ''" + grupo.getNome() + "'' já existente");
		}
		
		return grupoRepository.saveAndFlush(grupo);
	}
}
