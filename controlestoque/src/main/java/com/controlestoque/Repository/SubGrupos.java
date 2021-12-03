package com.controlestoque.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.model.Grupo;
import com.controlestoque.model.SubGrupo;

@Repository
public interface SubGrupos extends JpaRepository<SubGrupo, Long> {

	public List<SubGrupo> findByGrupoCodigo(Long codigoGrupo);

	public Optional<SubGrupo> findByNomeAndGrupo(String nome, Grupo grupo);
}
