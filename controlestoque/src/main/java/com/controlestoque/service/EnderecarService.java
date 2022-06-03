package com.controlestoque.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Enderecamentos;
import com.controlestoque.model.Enderecar;
import com.controlestoque.service.exception.ImpossivelExcluirEntidadeException;
import com.controlestoque.service.exception.enderecoOcupadoException;

@Service
public class EnderecarService {

	@Autowired
	private Enderecamentos enderecamentoRepository;

	@Transactional
	public Enderecar salvandoEndereco(Enderecar enderecar) {
		System.out.println(enderecar.getCodigo());

		Optional<Enderecar> enderecarOpt = enderecamentoRepository.findByCodigo(enderecar.getCodigo());

		if (enderecarOpt.isPresent() == false) {
			
			Optional<Enderecar> enderecarOptional = enderecamentoRepository.findByEndereco(enderecar.getEndereco());
			if (enderecarOptional.isPresent()) {

				throw new enderecoOcupadoException(
						"Endereço " + enderecar.getEndereco().getNomeEndereco() + " está ocupado.");
			}

		}

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
