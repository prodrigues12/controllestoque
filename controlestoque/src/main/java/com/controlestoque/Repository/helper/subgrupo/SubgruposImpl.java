package com.controlestoque.Repository.helper.subgrupo;

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

import com.controlestoque.Repository.filter.SubgrupoFilter;
import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.model.Subgrupo;

public class SubgruposImpl implements SubgruposQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Override
	@Transactional(readOnly = true)
	public Page<Subgrupo> filtrar(SubgrupoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Subgrupo.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		criteria.createAlias("grupo", "g");
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(SubgrupoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Subgrupo.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(SubgrupoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (filtro.getGrupo()!= null) {
				criteria.add(Restrictions.eq("grupo", filtro.getGrupo()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
		}
	}

}
