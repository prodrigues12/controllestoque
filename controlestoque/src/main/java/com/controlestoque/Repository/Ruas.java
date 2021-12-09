package com.controlestoque.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.model.Rua;

@Repository
public interface Ruas  extends JpaRepository<Rua, Long>{
	public Optional<Rua> findByNomeIgnoreCase(String nome);
	}