package com.controlestoque.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.Repository.helper.secao.SecoesQueries;
import com.controlestoque.model.Secao;

@Repository
public interface Secoes extends JpaRepository<Secao, Long>, SecoesQueries {

	public Optional<Secao> findByNomeIgnoreCase(String nome);
}
