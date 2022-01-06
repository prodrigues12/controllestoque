package com.controlestoque.service;

import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Apartamentos;
import com.controlestoque.model.Apartamento;

import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;
import com.controlestoque.service.exception.NomeSubGrupoExisteException;

@Service
public class ApartamentoService {

	@Autowired
	Apartamentos apartamentoRepository;

	@Transactional
	public void salvar(Apartamento apartamento) {
		Optional<Apartamento> apartamentoExistente = apartamentoRepository.findByNomeAndBloco(apartamento.getNome(),
				apartamento.getBloco());
		
		if(apartamentoExistente.isPresent()) {
			throw new NomeSubGrupoExisteException ("Sub-grupo já cadastrado nesse grupo");
		}
		
		apartamentoRepository.save(apartamento);
	}
	
	@Transactional
	public void excluir(Apartamento apartamento) {
	try {
		apartamentoRepository.delete(apartamento);
		apartamentoRepository.flush();
	} catch (PersistenceException e) {
		throw new ImpossivelExcluirEntidadeException("Impossivel apagar Rua. Exclua primeiros seus blocos.");
	}
		
	}

}
