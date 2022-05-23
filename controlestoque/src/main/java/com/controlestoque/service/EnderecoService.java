package com.controlestoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Enderecos;
import com.controlestoque.model.Endereco;
import com.controlestoque.service.exception.EnderecoJaCadastradoException;

@Service
public class EnderecoService {

	@Autowired
	private Enderecos endRepository;

	public Endereco salvandoEndereco(Endereco endereco) {
		Optional<Endereco> enderecoOptional = endRepository.findByNomeEndereco(endereco.getNomeEndereco());

		if (enderecoOptional.isPresent()) {
			throw new EnderecoJaCadastradoException("Enderecço " + endereco.getNomeEndereco() + " já cadastrado");
		}
		
		endereco.setStatus(true);

		return endRepository.save(endereco);
	}

}
