package com.itacademy.jd2.yi.cms.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itacademy.jd2.yi.cms.dao.api.IPageDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Page;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Page_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Template_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.UserAccount_;

@Repository
public class PageDaoImpl extends AbstractDaoImpl<IPage, Integer> implements IPageDao {

	protected PageDaoImpl() {
		super(Page.class);
	}

	@Override
	public IPage createEntity() {
		final Page page = new Page();
		return page;
	}

	@Override
	public void save(IPage... entities) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public IPage getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IPage> cq = cb.createQuery(IPage.class); // define returning result
		final Root<Page> from = cq.from(Page.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Page_.template, JoinType.LEFT);
		from.fetch(Page_.site, JoinType.LEFT);

		from.fetch(Page_.creator, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Page_.id), id)); // where id=?

		final TypedQuery<IPage> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IPage> find(PageFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPage> cq = cb.createQuery(IPage.class);
		final Root<Page> from = cq.from(Page.class);
		cq.select(from);

		from.fetch(Page_.creator, JoinType.LEFT);
		from.fetch(Page_.site, JoinType.LEFT);
		from.fetch(Page_.template, JoinType.LEFT);

		applyFilter(filter, cb, cq, from);

		// set sort params
		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IPage> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(PageFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Page> from = cq.from(Page.class);
		cq.select(cb.count(from));
		applyFilter(filter, cb, cq, from);
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private void applyFilter(final PageFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<Page> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String path = filter.getPath();
		if (!StringUtils.isEmpty(path)) {
			ands.add(cb.equal(from.get(Page_.path), path));
		}
		final String pageTitle = filter.getPageTitle();
		if (!StringUtils.isEmpty(pageTitle)) {
			ands.add(cb.equal(from.get(Page_.pageTitle), pageTitle));
		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	private Path<?> getSortPath(final Root<Page> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Page_.created);
		case "updated":
			return from.get(Page_.updated);
		case "id":
			return from.get(Page_.id);
		case "path":
			return from.get(Page_.path);
		case "page_title":
			return from.get(Page_.pageTitle);
		case "page_status":
			return from.get(Page_.pageStatus);
		case "template":
			return from.get(Page_.template).get(Template_.viewName);
		case "creator":
			return from.get(Page_.creator).get(UserAccount_.name);
		case "site":
			return from.get(Page_.site).get(Site_.name);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public List<IPage> search(String text) {

		EntityManager em = getEntityManager();
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		// create native Lucene query unsing the query DSL
		// alternatively you can write the Lucene query using the Lucene query
		// parser
		// or the Lucene programmatic API. The Hibernate Search DSL is
		// recommended though
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Page.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("pageTitle").matching(text).createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Page.class);

		return jpaQuery.getResultList();

	}

	@Override
	public IPage getByPagePath(String pagePath) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IPage> cq = cb.createQuery(IPage.class);

		final Root<Page> from = cq.from(Page.class);
		cq.select(from); 
		
		cq.where(cb.equal(from.get(Page_.path), pagePath));

		final TypedQuery<IPage> q = em.createQuery(cq);

		return getSingleResult(q);
	}

}
