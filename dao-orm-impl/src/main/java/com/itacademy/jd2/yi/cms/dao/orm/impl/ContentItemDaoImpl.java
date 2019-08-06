package com.itacademy.jd2.yi.cms.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.IContentItemDao;
import com.itacademy.jd2.yi.cms.dao.api.ISiteDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.ContentItemFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.ContentItem;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.ContentItem_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site;
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

		final Root<ContentItem> from = cq.from(ContentItem.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super ContentItem, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
			// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
			// by
			// column_name
			// order
		}
		final TypedQuery<IContentItem> q = em.createQuery(cq);
		setPaging(filter, q);

		return q.getResultList();
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
			return ContentItem_.html;
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

}
