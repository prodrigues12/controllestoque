package com.controlestoque.Repository.helper.rua;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.RuaFilter;
import com.controlestoque.model.Rua;

public interface RuasQueries {
	
	public Page<Rua>filtrar(RuaFilter filtro, Pageable pageable);

}
