package com.controlestoque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	@Query("select p from Funcionario p where p.nomeFuncionario like %?1%")
	List<Funcionario> findByNomeContainingIngnoreCase(String nomepesquisa);

	@Query("select p from Funcionario p where p.idFuncionario = :id")
	Funcionario tenhoCadastro (Long id); 

}
