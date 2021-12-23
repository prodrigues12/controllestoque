package com.controlestoque.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.Repository.helper.grupo.GruposQueries;
import com.controlestoque.model.Grupo;


@Repository
public interface Grupos extends JpaRepository<Grupo, Long>, GruposQueries {
	public Optional<Grupo> findByNomeIgnoreCase(String nome);
}
