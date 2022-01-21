package com.controlestoque.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.controlestoque.model.Sala;


@Repository
public interface Salas extends JpaRepository<Sala, Long> {

//	public List<Sala> findByBlocoCodigo(Long codigoSala);

	
	
	
}
