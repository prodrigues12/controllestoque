package com.controlestoque.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.SubGrupos;
import com.controlestoque.model.SubGrupo;
import com.controlestoque.service.exception.NomeSubGrupoExisteException;

@Service
public class SubGrupoService {

	@Autowired
	SubGrupos subGrupoRepository;

	@Transactional
	public void salvar(SubGrupo subGrupo) {
		Optional<SubGrupo> subGrupoExiste = subGrupoRepository.findByNomeAndGrupo(subGrupo.getNome(),
				subGrupo.getGrupo());
		
		if(subGrupoExiste.isPresent()) {
			throw new NomeSubGrupoExisteException ("Sub-grupo já cadastrado nesse grupo");
		}
		
		subGrupoRepository.save(subGrupo);
	}

}