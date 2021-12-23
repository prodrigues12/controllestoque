package com.controlestoque.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.model.Grupo;
import com.controlestoque.model.Subgrupo;

@Repository
public interface SubGrupos extends JpaRepository<Subgrupo, Long> {

	public List<Subgrupo> findByGrupoCodigo(Long codigoGrupo);

	public Optional<Subgrupo> findByNomeAndGrupo(String nome, Grupo grupo);
}
