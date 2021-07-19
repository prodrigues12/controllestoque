package com.controlestoque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlestoque.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	@Query("select p from Produto p where p.nome like %?1%")
	public List<Produto>findByNomeContainingIngnoreCase(String nome); 
	
	@Query("update Produto p set p.qtdEstoque=:valor where p.idProduto = :id")
	public Produto AjusteEtq(int valor, Long id);
}
