package com.controlestoque.Repository.helper.secao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.SecaoFilter;
import com.controlestoque.model.Secao;



public interface SecoesQueries {
	
	public Page<Secao>filtrar(SecaoFilter filtro, Pageable pageable);

}
