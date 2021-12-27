package com.controlestoque.Repository.helper.subgrupo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.SubgrupoFilter;
import com.controlestoque.model.Subgrupo;

public interface SubgruposQueries {
	
	public Page<Subgrupo>filtrar(SubgrupoFilter filtro, Pageable pageable);

}
