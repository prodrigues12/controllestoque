package com.controlestoque.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controlestoque.Repository.Secoes;
import com.controlestoque.model.Secao;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;
import com.controlestoque.service.exception.NomeSecaoExistenteException;

@Service
public class SecaoService {
	
	@Autowired 
	private Secoes secRepository;
	
	public Secao salvarSecao(Secao secao) {
		Optional<Secao> secaoOptional = secRepository.findByNomeIgnoreCase(secao.getNome());
		
		
		if(secaoOptional.isPresent()) {
			throw new NomeSecaoExistenteException("Nome: ''" +secao.getNome() + "'' já existente");
		}
		
		return secRepository.saveAndFlush(secao);
	}

	@Transactional
	public void excluir(Secao secao) {

		try {
			secRepository.delete(secao);
			secRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel apagar Secao. Remova os produtos dessa seção.");
		}
	}

}
