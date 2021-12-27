package com.controlestoque.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.Repository.helper.subgrupo.SubgruposQueries;
import com.controlestoque.model.Grupo;
import com.controlestoque.model.Subgrupo;

@Repository
public interface Subgrupos extends JpaRepository<Subgrupo, Long>, SubgruposQueries {

	public List<Subgrupo> findByGrupoCodigo(Long codigoGrupo);

	public Optional<Subgrupo> findByNomeAndGrupo(String nome, Grupo grupo);
}
