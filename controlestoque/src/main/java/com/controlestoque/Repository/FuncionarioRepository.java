package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlestoque.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	

}
