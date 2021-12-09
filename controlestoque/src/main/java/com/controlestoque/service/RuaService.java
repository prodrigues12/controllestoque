package com.controlestoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Ruas;
import com.controlestoque.model.Rua;
import com.controlestoque.service.exception.NomeRuaExistenteException;

@Service
public class RuaService {

	@Autowired
	Ruas ruaRepository;

	public Rua salvarRua(Rua rua) {
		Optional<Rua> ruaOptional = ruaRepository.findByNomeIgnoreCase(rua.getNome());

		if (ruaOptional.isPresent()) {
			throw new NomeRuaExistenteException("Nome: ''" + rua.getNome() + "'' já existente");
		}

		return ruaRepository.saveAndFlush(rua);
	}
}
