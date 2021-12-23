package com.controlestoque.Repository.helper.grupo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.GrupoFilter;
import com.controlestoque.model.Grupo;



public interface GruposQueries {
	
	public Page<Grupo>filtrar(GrupoFilter filtro, Pageable pageable);

}
