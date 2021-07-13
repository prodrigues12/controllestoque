package com.controlestoque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Secao;

public interface SecaoRepository extends JpaRepository<Secao, Long> {
	
	@Query("select p from Secao p where p.nomesecao like %?1%")
	public List<Secao>findByNomeContainingIngnoreCase(String nome); 
	
	@Query("select p.nomesecao from Secao p")
	public List<Secao>findAllNome(); 

}
