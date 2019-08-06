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

import com.itacademy.jd2.yi.cms.dao.api.ISiteDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site_;


@Repository
public class SiteDaoImpl  extends AbstractDaoImpl<ISite, Integer> implements ISiteDao {

    protected SiteDaoImpl() {
        super(Site.class);
    }

	@Override
	public ISite createEntity() {
		final Site site = new Site();
		return site;
	}

	@Override
	public void save(ISite... entities) {
		throw new RuntimeException ("not implemented");		
	}

	@Override
	public List<ISite> find(SiteFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ISite> cq = cb.createQuery(ISite.class);

		final Root<Site> from = cq.from(Site.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Site, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
			// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
			// by
			// column_name
			// order
		}

		final TypedQuery<ISite> q = em.createQuery(cq);
		setPaging(filter, q);

		return q.getResultList();
	}
	
	private SingularAttribute<? super Site, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Site_.created;
		case "updated":
			return Site_.updated;
		case "id":
			return Site_.id;
		case "name":
			return Site_.name;
		case "basepath":
			return Site_.basepath;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(SiteFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Site> from = cq.from(Site.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
