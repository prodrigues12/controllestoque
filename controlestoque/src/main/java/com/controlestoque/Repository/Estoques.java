package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlestoque.model.Estoque;

public interface Estoques extends JpaRepository<Estoque, Long>{

}
