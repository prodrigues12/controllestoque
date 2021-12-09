package com.controlestoque.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.model.Bloco;
import com.controlestoque.model.Rua;

@Repository
public interface Blocos extends JpaRepository<Bloco, Long>{
	public List<Bloco> findByRuaCodigo(Long codigoRua);

	public Optional<Bloco> findByNomeAndRua(String nome, Rua rua);
}