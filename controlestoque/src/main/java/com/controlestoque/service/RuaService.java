package com.controlestoque.service;

import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Ruas;
import com.controlestoque.model.Rua;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;
import com.controlestoque.service.exception.NomeRuaExistenteException;

@Service
public class RuaService {

	@Autowired
	Ruas ruaRepository;

	@Transactional
	public Rua salvarRua(Rua rua) {
		Optional<Rua> ruaOptional = ruaRepository.findByNomeIgnoreCase(rua.getNome());

		if (ruaOptional.isPresent()) {
			throw new NomeRuaExistenteException("Nome: ''" + rua.getNome() + "'' já existente");
		}

		return ruaRepository.saveAndFlush(rua);
	}

	@Transactional
	public void excluir(Rua rua) {
	try {
		ruaRepository.delete(rua);
		ruaRepository.flush();
	} catch (PersistenceException e) {
		throw new ImpossivelExcluirEntidadeException("Impossivel apagar Rua. Exclua primeiros seus blocos.");
	}
		
	}
}
