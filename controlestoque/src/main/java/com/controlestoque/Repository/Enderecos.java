package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlestoque.Repository.helper.endereco.EnderecosQueries;
import com.controlestoque.model.Endereco;

public interface Enderecos extends JpaRepository<Endereco, Long>, EnderecosQueries {

}
