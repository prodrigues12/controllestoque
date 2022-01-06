package com.controlestoque.Repository.helper.apartamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.ApartamentoFilter;
import com.controlestoque.model.Apartamento;

public interface ApartamentosQuery {
	
	
	public Page<Apartamento>filtrar(ApartamentoFilter filtro, Pageable pageable);


}
