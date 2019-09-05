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
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itacademy.jd2.yi.cms.dao.api.IContentItemDao;
import com.itacademy.jd2.yi.cms.dao.api.ISiteDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.ContentItemFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.ContentItem;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.ContentItem_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Page;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Page_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Template_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.UserAccount;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.UserAccount_;

@Repository
public class ContentItemDaoImpl  extends AbstractDaoImpl<IContentItem, Integer> implements IContentItemDao {

    protected ContentItemDaoImpl() {
        super(ContentItem.class);
    }

	@Override
	public IContentItem createEntity() {
		final ContentItem contentItem = new ContentItem();
		return contentItem;
	}

	@Override
	public void save(IContentItem... entities) {

	}

	@Override
	public List<IContentItem> find(ContentItemFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IContentItem> cq = cb.createQuery(IContentItem.class);
		final Root<ContentItem> from = cq.from(ContentItem.class);
		cq.select(from);

		from.fetch(ContentItem_.site, JoinType.LEFT);

		applyFilter(filter, cb, cq, from);

		// set sort params
		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IContentItem> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}
	
	private void applyFilter(final ContentItemFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<ContentItem> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String html = filter.getHtml();
		if (!StringUtils.isEmpty(html)) {
			ands.add(cb.equal(from.get(ContentItem_.html), html));
		}
		final String title = filter.getTitle();
		if (!StringUtils.isEmpty(title)) {
			ands.add(cb.equal(from.get(ContentItem_.title), title));
		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	private Path<?> getSortPath(final Root<ContentItem> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(ContentItem_.created);
		case "updated":
			return from.get(ContentItem_.updated);
		case "id":
			return from.get(ContentItem_.id);
		case "html":
			return from.get(ContentItem_.html);
		case "title":
			return from.get(ContentItem_.title);
		case "site_id":
			return from.get(ContentItem_.site).get(Site_.siteName);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
	
    @Override
    public IContentItem getFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();

        final CriteriaQuery<IContentItem> cq = cb.createQuery(IContentItem.class); // define returning result
        final Root<ContentItem> from = cq.from(ContentItem.class); // define table for select

        cq.select(from); // define what need to be selected

        from.fetch(ContentItem_.site, JoinType.LEFT);

        cq.distinct(true); // to avoid duplicate rows in result

        // .. where id=...
        cq.where(cb.equal(from.get(ContentItem_.id), id)); // where id=?

        final TypedQuery<IContentItem> q = em.createQuery(cq);

        return getSingleResult(q);
    }
	
	private SingularAttribute<? super ContentItem, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return ContentItem_.created;
		case "updated":
			return ContentItem_.updated;
		case "id":
			return ContentItem_.id;
		case "html":
			return ContentItem_.html;
		case "title":
			return ContentItem_.title;
		case "site":
			return ContentItem_.site;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(ContentItemFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<ContentItem> from = cq.from(ContentItem.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}
	
	@Override

 	public List<IContentItem> search(String text) {

 		EntityManager em = getEntityManager();
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

 		// create native Lucene query unsing the query DSL
		// alternatively you can write the Lucene query using the Lucene query
		// parser
		// or the Lucene programmatic API. The Hibernate Search DSL is
		// recommended though
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Page.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("title").matching(text).createQuery();

 		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Page.class);

 		return jpaQuery.getResultList();

 	}
	
    @Override
    public IContentItem getFullInfo(final String content) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();

        final CriteriaQuery<IContentItem> cq = cb.createQuery(IContentItem.class); // define returning result
        final Root<ContentItem> from = cq.from(ContentItem.class); // define table for select

        cq.select(from); // define what need to be selected

        from.fetch(ContentItem_.site, JoinType.LEFT);

        cq.distinct(true); // to avoid duplicate rows in result

        // .. where id=...
        cq.where(cb.equal(from.get(ContentItem_.html), content)); // where id=?

        final TypedQuery<IContentItem> q = em.createQuery(cq);

        return getSingleResult(q);
    }

}
