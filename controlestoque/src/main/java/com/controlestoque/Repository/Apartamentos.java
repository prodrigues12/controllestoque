package com.controlestoque.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.model.Apartamento;
import com.controlestoque.model.Bloco;

@Repository
public interface Apartamentos extends JpaRepository<Apartamento, Long> {

	public List<Apartamento> findByBlocoCodigo(Long codigoBloco);

	public Optional<Apartamento> findByNomeAndBloco(String nome, Bloco bloco);
}
