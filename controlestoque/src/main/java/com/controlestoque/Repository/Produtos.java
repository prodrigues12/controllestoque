package com.controlestoque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.controlestoque.Repository.helper.produtos.ProdutosQueries;
import com.controlestoque.dto.ProdutosTopFive;
import com.controlestoque.model.Produto;

@Repository
public interface Produtos extends JpaRepository<Produto, Long>, ProdutosQueries{

	@Query("select p from Produto p where p.nome like %?1%")
	public List<Produto>findByNomeContainingIngnoreCase(String nome); 
	
	@Query("select p from Produto p where p.codigo = :id")
	public Produto ajusteEtq(Long id);
	
	@Query("select p from Produto p where p.qtdEstoque <= p.qtdEstMin")
	public List<Produto> baixoEtq();
	
	@Query("select p from Produto p where p.codigo =:codigo")
	public Produto findByCodigo(Long codigo);
	
	@Query("select p.nome from Produto p where p.codigo =:codigo")
	public String findByNome(Long codigo);

	@Query("select p from Produto p where p.codigo =0")
	public Produto ProdutoPallet();
	
}
	
