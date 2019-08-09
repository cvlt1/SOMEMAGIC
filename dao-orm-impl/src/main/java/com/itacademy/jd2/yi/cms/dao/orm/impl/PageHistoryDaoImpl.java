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

import com.itacademy.jd2.yi.cms.dao.api.IPageHistoryDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageHistoryFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Page;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.PageHistory;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.PageHistory_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Page_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.UserAccount_;
@Repository
public class PageHistoryDaoImpl extends AbstractDaoImpl<IPageHistory, Integer> implements IPageHistoryDao {

    protected PageHistoryDaoImpl() {
        super (PageHistory.class);
    }

	@Override
	public IPageHistory createEntity() {
        final PageHistory pageHistory = new PageHistory();
        return pageHistory;
	}

	@Override
	public void save(IPageHistory... entities) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public List<IPageHistory> find(PageHistoryFilter filter) {
		 final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	        final CriteriaQuery<IPageHistory> cq = cb.createQuery(IPageHistory.class);
	        final Root<PageHistory> from = cq.from(PageHistory.class);
	        cq.select(from);

	        from.fetch(PageHistory_.page, JoinType.LEFT);
	        from.fetch(PageHistory_.changedBy, JoinType.LEFT);

	        applyFilter(filter, cb, cq, from);

	        // set sort params
	        if (filter.getSortColumn() != null) {
	            final Path<?> expression = getSortPath(from, filter.getSortColumn());
	            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
	        }

	        final TypedQuery<IPageHistory> q = em.createQuery(cq);
	        setPaging(filter, q);
	        return q.getResultList();
	}
	
    @Override
    public IPageHistory getFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();

        final CriteriaQuery<IPageHistory> cq = cb.createQuery(IPageHistory.class); // define returning result
        final Root<PageHistory> from = cq.from(PageHistory.class); // define table for select

        cq.select(from); // define what need to be selected

        from.fetch(PageHistory_.page, JoinType.LEFT);
        from.fetch(PageHistory_.changedBy, JoinType.LEFT);

        cq.distinct(true); // to avoid duplicate rows in result

        // .. where id=...
        cq.where(cb.equal(from.get(PageHistory_.id), id)); // where id=?

        final TypedQuery<IPageHistory> q = em.createQuery(cq);

        return getSingleResult(q);
    }

	@Override
	public long getCount(PageHistoryFilter filter) {
		final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<PageHistory> from = cq.from(PageHistory.class);
        cq.select(cb.count(from));
        applyFilter(filter, cb, cq, from);
        final TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}
	
	private void applyFilter(final PageHistoryFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
            final Root<PageHistory> from) {
        final List<Predicate> ands = new ArrayList<>();

        final String content = filter.getComment();
        if (!StringUtils.isEmpty(content)) {
            ands.add(cb.equal(from.get(PageHistory_.comment), content));
        }
        
	}
	
	
    private Path<?> getSortPath(final Root<PageHistory> from, final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return from.get(PageHistory_.created);
        case "updated":
            return from.get(PageHistory_.updated);
        case "id":
            return from.get(PageHistory_.id);
        case "comment":
            return from.get(PageHistory_.comment);
        case "path":
            return from.get(PageHistory_.page).get(Page_.path);
        case "changed_by":
            return from.get(PageHistory_.changedBy).get(UserAccount_.id);
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }

}
