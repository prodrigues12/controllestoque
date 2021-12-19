package com.controlestoque.Repository.helper.secao;

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
import org.springframework.util.StringUtils;

import com.controlestoque.Repository.filter.SecaoFilter;
import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.model.Secao;

public class SecoesImpl implements SecoesQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Override
	public Page<Secao> filtrar(SecaoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Secao.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));

	}

	private Long total(SecaoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Secao.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();

	}

	private void adicionarFiltro(SecaoFilter filtro, Criteria criteria) {

		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.ilike("nome", filtro.getCodigo()));
			}
		}

	}

}
