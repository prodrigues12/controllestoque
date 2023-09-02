package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlestoque.model.ValorCusto;

@Repository
public interface ValorCustos extends JpaRepository<ValorCusto, Long> {

}
