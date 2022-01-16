package com.controlestoque.Repository.helper.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.hibernate.Criteria;
//import org.hibernate.Hibernate;
//import org.hibernate.Session;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.MatchMode;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.hibernate.criterion.Subqueries;
//import org.hibernate.sql.JoinType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;

//import com.controlestoque.Repository.paginacao.PaginacaoUtil;
//import com.controlestoque.model.GrupoUser;
import com.controlestoque.model.Usuario;

public class UsuariosImpl implements UsuariosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		// TODO Auto-generated method stub
		return manager.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

	@Override
	public List<String> permissoes(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
