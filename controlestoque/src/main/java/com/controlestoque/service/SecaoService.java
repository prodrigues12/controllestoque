package com.controlestoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.SecaoRepository;
import com.controlestoque.model.Secao;
import com.controlestoque.service.exception.NomeSecaoExistenteException;

@Service
public class SecaoService {
	
	@Autowired 
	private SecaoRepository secRepository;
	
	public Secao salvarSecao(Secao secao) {
		Optional<Secao> secaoOptional = secRepository.findByNomeIgnoreCase(secao.getNome());
		
		
		if(secaoOptional.isPresent()) {
			throw new NomeSecaoExistenteException("Nome: ''" +secao.getNome() + "'' já existente");
		}
		
		return secRepository.saveAndFlush(secao);
	}

}
