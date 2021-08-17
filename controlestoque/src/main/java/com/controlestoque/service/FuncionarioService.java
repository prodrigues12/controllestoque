package com.controlestoque.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.controlestoque.Exception.SemCadastroException;
import com.controlestoque.Repository.FuncionarioRepository;
import com.controlestoque.model.Funcionario;

public class FuncionarioService {

	@Autowired
	FuncionarioRepository funRepository;

	public void verificarCadastro(Funcionario fun) throws Exception {

		try {
			if (funRepository.tenhoCadastro(fun.getIdFuncionario()) != null);
					System.out.println("      tem cad   ");
			

		} catch (Exception e) {
			throw new SemCadastroException("### *** >>> Sem cadastro <<< *** ###");
		}

	}

}
