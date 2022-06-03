package com.controlestoque.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlestoque.Repository.helper.enderecamento.EnderecamentosQueries;
import com.controlestoque.model.Enderecar;
import com.controlestoque.model.Endereco;

public interface Enderecamentos extends JpaRepository<Enderecar, Long>, EnderecamentosQueries {

	public Optional<Enderecar> findByEndereco(Endereco endereco);

	public Optional<Enderecar> findByCodigo(Long codigo);

}
