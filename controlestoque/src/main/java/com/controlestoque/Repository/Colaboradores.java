package com.controlestoque.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlestoque.Repository.helper.colaborador.ColaboradoresQueries;
import com.controlestoque.model.Colaborador;


public interface Colaboradores extends JpaRepository<Colaborador, Long>, ColaboradoresQueries{

	public Optional<Colaborador> findByCpfCnpjId(String cpfCnpjId);

}
