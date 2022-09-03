package com.controlestoque.Repository.helper.produtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.controlestoque.Enums.StatusPedido;
import com.controlestoque.Repository.filter.ProdutoFilter;
import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.dto.PedidosMes;
import com.controlestoque.dto.ProdutoDTO;
import com.controlestoque.dto.ProdutosTopFive;
import com.controlestoque.model.Produto;

public class ProdutosImpl implements ProdutosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUltil;

	//Produto - Filtrar busca dos produtos
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		paginacaoUltil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Produto> estoqueMinimo(ProdutoFilter filtro, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		filtroEstoqueMinimo(filtro, criteria);

		paginacaoUltil.preparar(criteria, pageable);

		

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Produto> estoqueZerado(ProdutoFilter filtro, Pageable pageable) {
		
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		filtroEstoqueZerado(filtro, criteria);

		paginacaoUltil.preparar(criteria, pageable);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(ProdutoFilter filtro) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	private void adicionarFiltro(ProdutoFilter filtro, Criteria criteria) {
		
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (filtro.getUniMedida() != null) {
				criteria.add(Restrictions.eq("uniMedida", filtro.getUniMedida()));
			}

			if (isSecaoPresente(filtro)) {
				criteria.add(Restrictions.eq("secao", filtro.getSecao()));
			}

			if (filtro.getQtdEstoque() != null) {
				criteria.add(Restrictions.eq("qtdEstoque", filtro.getQtdEstoque()));
			}

		}
	}

	@SuppressWarnings("deprecation")
	private void filtroEstoqueMinimo(ProdutoFilter filtro, Criteria criteria) {

		criteria.add(Restrictions.sqlRestriction("qtd_estoque <= qtd_est_min"));
		
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (filtro.getUniMedida() != null) {
				criteria.add(Restrictions.eq("uniMedida", filtro.getUniMedida()));
			}

			if (isSecaoPresente(filtro)) {
				criteria.add(Restrictions.eq("secao", filtro.getSecao()));
			}

			if (filtro.getQtdEstoque() != null) {
				criteria.add(Restrictions.eq("qtdEstoque", filtro.getQtdEstoque()));
			}

		}
	}

	private void filtroEstoqueZerado(ProdutoFilter filtro, Criteria criteria) {
		criteria.add(Restrictions.eq("qtdEstoque", BigDecimal.ZERO));
		
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (filtro.getUniMedida() != null) {
				criteria.add(Restrictions.eq("uniMedida", filtro.getUniMedida()));
			}

			if (isSecaoPresente(filtro)) {
				criteria.add(Restrictions.eq("secao", filtro.getSecao()));
			}

			if (filtro.getQtdEstoque() != null) {
				criteria.add(Restrictions.eq("qtdEstoque", filtro.getQtdEstoque()));
			}

		}
	}

	@Override
	// buscar lista de produtos no pedidoNovo
	public List<ProdutoDTO> codigoOuNome(String codigoOuNome) {
		String jpql = "select new com.controlestoque.dto.ProdutoDTO (codigo, nome)"
				+ "from Produto where lower(nome) like lower(:codigoOuNome) or lower(codigo) like lower(:codigoOuNome)";
		List<ProdutoDTO> produtosFiltrados = manager.createQuery(jpql, ProdutoDTO.class)
				.setParameter("codigoOuNome", "%" + codigoOuNome + "%").getResultList();

		return produtosFiltrados;
	}

	private boolean isSecaoPresente(ProdutoFilter filtro) {
		return filtro.getSecao() != null && filtro.getSecao().getCodigo() != null;
	}

	@Override
	public Produto buscaCompleta(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		criteria.createAlias("blocos", "b", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Produto) criteria.uniqueResult();
	}

	//Dashboard - total de produtos
	public BigDecimal totalItensEstoque() {
		return manager.createQuery("select sum(qtdEstoque) from Produto", BigDecimal.class).getSingleResult();
	}
	
	//Dashboard - produtos estoque baixo
	public Long estoqueBaixo() {
		Optional<Long> optional = Optional
				.ofNullable(manager.createQuery("select count(*) from Produto where qtdEstMin >= qtdEstoque", Long.class)
						.getSingleResult());

		return optional.orElse(Long.valueOf(0));
	}

	//Dashboard - produtos estoque Zero
	public Long estoqueZero() {
		Optional<Long> optional = Optional.ofNullable(manager
				.createQuery("select count(*) from Produto where qtdEstoque <= 0", Long.class).getSingleResult());

		return optional.orElse(Long.valueOf(0));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProdutosTopFive> topFiveProdutos() {
		
		String query = "SELECT  produto.nome AS nome, count(item.quantidade) AS quantidade "
				+ "FROM item_pedido item INNER JOIN produto produto  INNER JOIN pedido pedido "
				+ "ON item.codigo_produto = produto.codigo AND item.codigo_pedido = pedido.codigo "
				+ "AND pedido.data_criacao BETWEEN CURDATE() - INTERVAL 6 MONTH AND CURDATE() "
				+ "AND pedido.status='FINALIZADO'"
				+ "GROUP BY produto.nome ORDER BY COUNT(item.quantidade) DESC LIMIT 5";
				
		
		Query nativeQuery = manager.createNativeQuery(query, "mappingProtutosFive");
		List<ProdutosTopFive> lista = nativeQuery.getResultList();

		return lista;
		
	}

}
