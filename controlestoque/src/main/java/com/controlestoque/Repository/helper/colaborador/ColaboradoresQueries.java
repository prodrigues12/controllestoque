package com.controlestoque.Repository.helper.colaborador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.ColaboradorFilter;
import com.controlestoque.model.Colaborador;

public interface ColaboradoresQueries {

	public Page<Colaborador> filtrar (ColaboradorFilter filtro, Pageable pageable);
}
