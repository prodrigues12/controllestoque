package com.controlestoque.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Subgrupos;
import com.controlestoque.model.Subgrupo;
import com.controlestoque.service.exception.NomeSubGrupoExisteException;

@Service
public class SubGrupoService {

	@Autowired
	Subgrupos subGrupoRepository;

	@Transactional
	public void salvar(Subgrupo subGrupo) {
		Optional<Subgrupo> subGrupoExiste = subGrupoRepository.findByNomeAndGrupo(subGrupo.getNome(),
				subGrupo.getGrupo());
		
		if(subGrupoExiste.isPresent()) {
			throw new NomeSubGrupoExisteException ("Sub-grupo já cadastrado nesse grupo");
		}
		
		subGrupoRepository.save(subGrupo);
	}

}
