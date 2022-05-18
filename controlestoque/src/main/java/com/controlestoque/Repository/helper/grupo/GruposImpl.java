package com.controlestoque.Repository.helper.grupo;

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

import com.controlestoque.Repository.filter.BlocoFilter;
import com.controlestoque.Repository.filter.GrupoFilter;
import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.model.Grupo;

public class GruposImpl implements GruposQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Transactional(readOnly = true)
	public Page<Grupo> filtrar(GrupoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Grupo.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(GrupoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Grupo.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();

	}

	private void adicionarFiltro(GrupoFilter filtro, Criteria criteria) {

		if (filtro != null) {

			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if (isSecaoPresente(filtro)) {
				criteria.add(Restrictions.eq("secao", filtro.getSecao()));
			}

		}
	}
	
	private boolean isSecaoPresente(GrupoFilter filtro) {
		return filtro.getSecao() != null && filtro.getSecao().getCodigo() != null;
	}
}
