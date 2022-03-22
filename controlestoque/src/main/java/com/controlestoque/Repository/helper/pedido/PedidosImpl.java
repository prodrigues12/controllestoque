package com.controlestoque.Repository.helper.pedido;


import java.time.LocalDate;

import java.util.Optional;

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

import com.controlestoque.Enums.StatusPedido;
import com.controlestoque.Enums.TipoIdentificacao;
import com.controlestoque.Repository.filter.PedidoFilter;
import com.controlestoque.Repository.paginacao.PaginacaoUtil;
import com.controlestoque.model.Pedido;

public class PedidosImpl implements PedidosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUltil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Pedido> filtrar(PedidoFilter filter, Pageable pageable) {

		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pedido.class);

		paginacaoUltil.preparar(criteria, pageable);
		adicionarFiltro(filter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	@Override
	@Transactional(readOnly = true)
	public Pedido buscarComItens(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pedido.class);
		criteria.createAlias("itens", "i", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Pedido) criteria.uniqueResult();

	}

	@SuppressWarnings("deprecation")
	private void adicionarFiltro(PedidoFilter filtro, Criteria criteria) {
		criteria.createAlias("colaborador", "c");

		if (filtro != null) {

			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}

			if (filtro.getStatus() != null) {
				criteria.add(Restrictions.eq("status", filtro.getStatus()));
			}

			if (filtro.getDesde() != null) {
				if (filtro.getDesde().isBlank()) {
					filtro.setDesde(null);

				} else {
					LocalDate localDate = LocalDate.parse(filtro.getDesde());
					criteria.add(Restrictions.ge("dataCriacao", localDate));

				}
			}

			if (filtro.getAte() != null) {
				if (filtro.getAte().isBlank()) {
					filtro.setAte(filtro.getDesde());

				} else {
					LocalDate localDate = LocalDate.parse(filtro.getAte());
					criteria.add(Restrictions.le("dataCriacao", localDate));

				}

			}

			if (filtro.getTurno() != null) {
				criteria.add(Restrictions.eq("turno", filtro.getTurno()));
			}

			if (!StringUtils.isEmpty(filtro.getNomeColaborador())) {
				criteria.add(Restrictions.ilike("c.nome", filtro.getNomeColaborador(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(filtro.getCpfCnpjId())) {
				criteria.add(
						Restrictions.eq("c.cpfCnpjId", TipoIdentificacao.removerFormatacao(filtro.getCpfCnpjId())));
			}
		}
	}

	@Override
	public Long statusIgualNovo() {
		Optional<Long> optional = Optional
				.ofNullable(manager.createQuery("select count(*) from Pedido where status= :status", Long.class)
						.setParameter("status", StatusPedido.NOVO).getSingleResult());

		return optional.orElse(Long.valueOf(0));
	}

	@Override
	public Long statusIgualFinalizado() {
		Optional<Long> optional = Optional
				.ofNullable(manager.createQuery("select count(*) from Pedido where status= :status", Long.class)
						.setParameter("status", StatusPedido.FINALIZADO).getSingleResult());

		return optional.orElse(Long.valueOf(0));
	}

	@Override
	public Long statusIgualEspera() {
		Optional<Long> optional = Optional
				.ofNullable(manager.createQuery("select count(*) from Pedido where status= :status", Long.class)
						.setParameter("status", StatusPedido.ESPERA).getSingleResult());

		return optional.orElse(Long.valueOf(0));
	}

	@Override
	public Long statusIgualCancelado() {
		Optional<Long> optional = Optional
				.ofNullable(manager.createQuery("select count(*) from Pedido where status= :status", Long.class)
						.setParameter("status", StatusPedido.CANCELADO).getSingleResult());

		return optional.orElse(Long.valueOf(0));
	}

	private Long total(PedidoFilter filtro) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pedido.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

}