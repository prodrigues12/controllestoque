package com.controlestoque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Grupo;


public interface GrupoRepository extends JpaRepository<Grupo, Long>{

	@Query("select p from Secao p where p.nomesecao like %?1%")
	public List<Grupo>findByNomeContainingIngnoreCase(String nome);
}
