package com.controlestoque.service;

import java.time.LocalDate;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Enderecamentos;
import com.controlestoque.model.Enderecar;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class EnderecarService {

	@Autowired
	private Enderecamentos enderecamentoRepository;

	@Transactional
	public Enderecar salvandoEndereco(Enderecar enderecar) {

		enderecar.setDataAlteracao(LocalDate.now());

		return enderecamentoRepository.save(enderecar);
	}

	@Transactional
	public void excluir(Enderecar enderecar) {
		try {
			enderecamentoRepository.delete(enderecar);
			enderecamentoRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException(
					"Impossivel apagar Endereço. Remova os produto e os endereços dessa .");
		}

	}

}
