package com.controlestoque.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.Repository.helper.colaborador.ColaboradoresQueries;
import com.controlestoque.model.Colaborador;

@Repository
public interface Colaboradores extends JpaRepository<Colaborador, Long>, ColaboradoresQueries{

	public Optional<Colaborador> findByCpfCnpjId(String cpfCnpjId);

}
