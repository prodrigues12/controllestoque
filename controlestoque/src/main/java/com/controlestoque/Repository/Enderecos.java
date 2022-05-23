package com.controlestoque.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.Repository.helper.endereco.EnderecosQueries;
import com.controlestoque.model.Endereco;

public interface Enderecos extends JpaRepository<Endereco, Long>, EnderecosQueries {

	Optional<Endereco> findByNomeEndereco(String nomeEndereco);
	
	@Query("Select e from Endereco e where e.status=0 ")
	List<Endereco> findbyStatusFalse();

}
