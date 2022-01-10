package com.controlestoque.Repository.helper.colaborador;

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

import com.controlestoque.Repository.filter.ColaboradorFilter;
import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.model.Colaborador;

public class ColaboradoresImpl implements ColaboradoresQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUltil;

	@Transactional(readOnly = true)
	public Page<Colaborador> filtrar(ColaboradorFilter filtro, Pageable pageable) {	
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Colaborador.class);
		
		paginacaoUltil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(ColaboradorFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Colaborador.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}


	private void adicionarFiltro(ColaboradorFilter filtro, Criteria criteria) {

		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.eq("tipoIdentificacao", filtro.getTipoIdentificacao()));
			}
			
			if (!StringUtils.isEmpty(filtro.getCpfCnpjId())) {
				criteria.add(Restrictions.ilike("cpfCnpjId", filtro.getCpfCnpjId()));
			}
//			
		}
	
	}

}
