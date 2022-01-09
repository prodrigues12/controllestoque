package com.controlestoque.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Colaboradores;
import com.controlestoque.model.Colaborador;
import com.controlestoque.service.exception.CpfCnpjIdJaCadastradaException;

@Service
public class ColaboradorService {
	
	@Autowired
	private Colaboradores colRepository;
	
	@Transactional
	public Colaborador salvar(Colaborador colaborador) {
		Optional<Colaborador> colabExistente = colRepository.findByCpfCnpjId(colaborador.getCpfCnpjIdSemFormatacao());
		if(colabExistente.isPresent()) {
			throw new CpfCnpjIdJaCadastradaException("ID/CPF/CNPJ já cadastrado");

		}
		colRepository.save(colaborador);
		
		return colaborador;
	}

	@Transactional
	public void excluir(Colaborador colaborador) {
		try {
			colRepository.delete(colaborador);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
