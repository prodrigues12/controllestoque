package com.controlestoque.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.controlestoque.Repository.helper.enderecamento.EnderecamentosQueries;
import com.controlestoque.model.Enderecar;


public interface Enderecamentos extends JpaRepository<Enderecar, Long> , EnderecamentosQueries{


}
