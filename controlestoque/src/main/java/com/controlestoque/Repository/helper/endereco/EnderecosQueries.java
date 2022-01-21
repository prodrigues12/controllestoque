package com.controlestoque.Repository.helper.endereco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.EnderecoFilter;
import com.controlestoque.model.Endereco;

public interface EnderecosQueries {
	
	public Page<Endereco>filtrar(EnderecoFilter filtro, Pageable pageable);

}
