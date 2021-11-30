package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.model.Secao;

@Repository
public interface SecaoRepository extends JpaRepository<Secao, Long>{

	
}
