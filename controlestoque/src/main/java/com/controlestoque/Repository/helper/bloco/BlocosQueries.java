package com.controlestoque.Repository.helper.bloco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.BlocoFilter;
import com.controlestoque.model.Bloco;

public interface BlocosQueries {
	
	public Page<Bloco> filtrar(BlocoFilter blocoFilter, Pageable pageable);

}
