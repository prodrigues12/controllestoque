package com.controlestoque.service;

import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Blocos;
import com.controlestoque.model.Bloco;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;
import com.controlestoque.service.exception.NomeBlocoExistenteException;

@Service
public class BlocoService {
	
	@Autowired
	private Blocos blocoRepository;

	@Transactional
	public Bloco salvarBloco(Bloco bloco) {
		Optional<Bloco> blocoOptional = blocoRepository.findByNomeAndRua(bloco.getNome(), bloco.getRua());
		if (blocoOptional.isPresent()) {
			throw new NomeBlocoExistenteException("Nome: ''" + bloco.getNome() + "'' já existente");
		}

		return blocoRepository.saveAndFlush(bloco);
	}

	@Transactional
	public void excluir(Bloco bloco) {
		try {
			blocoRepository.delete(bloco);
			blocoRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel apagar Bloco. Exclua primeiro os apartamentos.");
		}
		
		
	}

}
