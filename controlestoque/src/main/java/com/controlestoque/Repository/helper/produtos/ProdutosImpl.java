package com.controlestoque.Repository.helper.produtos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import com.controlestoque.Repository.filter.ProdutoFilter;
import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.model.Produto;

public class ProdutosImpl implements ProdutosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUltil;

	@Transactional(readOnly = true)
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);

		paginacaoUltil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(ProdutoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ProdutoFilter filtro, Criteria criteria) {
		// TODO Auto-generated method stub
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if(filtro.getUniMedida()!=null) {
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

	private boolean isSecaoPresente(ProdutoFilter filtro) {
		return filtro.getSecao() != null && filtro.getSecao().getCodigo() != null;
	}

}
