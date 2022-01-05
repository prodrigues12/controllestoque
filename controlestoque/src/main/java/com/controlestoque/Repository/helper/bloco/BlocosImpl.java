package com.controlestoque.Repository.helper.bloco;

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

import com.controlestoque.Repository.filter.BlocoFilter;
import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.model.Bloco;

public class BlocosImpl implements BlocosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUltil;

	@Override
	public Page<Bloco> filtrar(BlocoFilter filtro, Pageable pageable) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Bloco.class);

		paginacaoUltil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(BlocoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Bloco.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(BlocoFilter filtro, Criteria criteria) {

		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if (isRuaPresente(filtro)) {
				criteria.add(Restrictions.eq("rua", filtro.getRua()));
			}
		}
	}
	
	private boolean isRuaPresente(BlocoFilter filtro) {
		return filtro.getRua() != null && filtro.getRua().getCodigo() != null;
	}
}
