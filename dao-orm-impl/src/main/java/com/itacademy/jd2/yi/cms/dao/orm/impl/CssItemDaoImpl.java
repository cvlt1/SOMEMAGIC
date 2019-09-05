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
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itacademy.jd2.yi.cms.dao.api.ICssItemDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.filter.CssItemFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.CssItem;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.CssItem_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Page;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Page_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site_;
@Repository
public class CssItemDaoImpl extends AbstractDaoImpl<ICssItem, Integer> implements ICssItemDao {

    protected CssItemDaoImpl() {
        super (CssItem.class);
    }
    
	@Override
	public ICssItem createEntity() {
		final CssItem cssItem = new CssItem();
		return cssItem;
	}

	@Override
	public List<ICssItem> find(CssItemFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ICssItem> cq = cb.createQuery(ICssItem.class);
        final Root<CssItem> from = cq.from(CssItem.class);
        cq.select(from);

        from.fetch(CssItem_.site, JoinType.LEFT);

        applyFilter(filter, cb, cq, from);

        // set sort params
        if (filter.getSortColumn() != null) {
            final Path<?> expression = getSortPath(from, filter.getSortColumn());
            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
        }

        final TypedQuery<ICssItem> q = em.createQuery(cq);
        setPaging(filter, q);
        return q.getResultList();
	}
	
	 @Override
	    public ICssItem getFullInfo(final Integer id) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();

	        final CriteriaQuery<ICssItem> cq = cb.createQuery(ICssItem.class); // define returning result
	        final Root<CssItem> from = cq.from(CssItem.class); // define table for select

	        cq.select(from); // define what need to be selected

	        from.fetch(CssItem_.site, JoinType.LEFT);

	        cq.distinct(true); // to avoid duplicate rows in result

	        // .. where id=...
	        cq.where(cb.equal(from.get(CssItem_.id), id)); // where id=?

	        final TypedQuery<ICssItem> q = em.createQuery(cq);

	        return getSingleResult(q);
	    }

	@Override
	public long getCount(CssItemFilter filter) {
		final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<CssItem> from = cq.from(CssItem.class);
        cq.select(cb.count(from));
        applyFilter(filter, cb, cq, from);
        final TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
    
	}
	
	private void applyFilter(final CssItemFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
            final Root<CssItem> from) {
        final List<Predicate> ands = new ArrayList<>();

        final String content = filter.getContent();
        if (!StringUtils.isEmpty(content)) {
            ands.add(cb.equal(from.get(CssItem_.content), content));
        }
	}
	
    private Path<?> getSortPath(final Root<CssItem> from, final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return from.get(CssItem_.created);
        case "updated":
            return from.get(CssItem_.updated);
        case "id":
            return from.get(CssItem_.id);
        case "sold":
            return from.get(CssItem_.content);
        case "model":
            return from.get(CssItem_.site).get(Site_.siteName);
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }
	
	//TODO add save method
	

}
