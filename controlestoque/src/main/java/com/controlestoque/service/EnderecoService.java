package com.controlestoque.service;

import java.time.LocalDate;
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

		if (endereco.isEnderecoNovo()) {
			System.out.println("endereco Novo");

			Optional<Endereco> enderecoOptional = endRepository.findByNomeEndereco(endereco.getNomeEndereco());

			if (enderecoOptional.isPresent()) {
				throw new EnderecoJaCadastradoException("Enderecço " + endereco.getNomeEndereco() + " já cadastrado");
			}

		} else {
			System.out.println("endereco existe");
		}

		endereco.setDataAlteracao(LocalDate.now());
		endereco.setStatus(true);

		return endRepository.save(endereco);
	}

	public Endereco salvandoEndereamentoo(Endereco endereco) {

		return endRepository.saveAndFlush(endereco);
	}

}
