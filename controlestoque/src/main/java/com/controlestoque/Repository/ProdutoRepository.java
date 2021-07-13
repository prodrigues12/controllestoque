package com.controlestoque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlestoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
