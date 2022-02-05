package com.controlestoque.Repository.helper.produtos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

import com.controlestoque.Repository.filter.ProdutoFilter;
import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.dto.ProdutoDTO;
import com.controlestoque.model.Produto;

public class ProdutosImpl implements ProdutosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUltil;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);

		paginacaoUltil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		criteria.createAlias("agrupar.subgrupo", "s", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("s.grupo", "g", JoinType.LEFT_OUTER_JOIN);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(ProdutoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

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

	@Override
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

}
