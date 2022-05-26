package com.controlestoque.Repository.helper.enderecamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.model.Enderecar;

public interface EnderecamentosQueries {
	
	public Page<Enderecar>filtrar(Enderecar filtro, Pageable pageable);

}
