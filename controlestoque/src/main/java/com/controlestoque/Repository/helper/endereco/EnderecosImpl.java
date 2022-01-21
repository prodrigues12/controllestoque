package com.controlestoque.Repository.helper.endereco;

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
import org.springframework.util.StringUtils;

import com.controlestoque.Repository.filter.EnderecoFilter;

import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.model.Endereco;
import com.controlestoque.model.Produto;

public class EnderecosImpl implements EnderecosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUltil;

	@Override
	public Page<Endereco> filtrar(EnderecoFilter filtro, Pageable pageable) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Endereco.class);

		paginacaoUltil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(EnderecoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Endereco.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(EnderecoFilter filtro, Criteria criteria) {

		if (filtro != null) {
//			if (!StringUtils.isEmpty(filtro.getCodigo())) {
//				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
//			}

			if (!StringUtils.isEmpty(filtro.getProduto())) {
				criteria.add(Restrictions.eq("produto", filtro.getProduto()));
			}
			
			if (!StringUtils.isEmpty(filtro.getRua())) {
				criteria.add(Restrictions.eq("rua", filtro.getRua()));
			}
			
			if (!StringUtils.isEmpty(filtro.getBloco())) {
				criteria.add(Restrictions.eq("bloco", filtro.getBloco()));
			}
			
			if (!StringUtils.isEmpty(filtro.getApartamento())) {
				criteria.add(Restrictions.eq("apartamento", filtro.getApartamento()));
			}

			if (!StringUtils.isEmpty(filtro.getSala())) {
				criteria.add(Restrictions.eq("sala", filtro.getSala()));
			}
		}
	}

	
}
