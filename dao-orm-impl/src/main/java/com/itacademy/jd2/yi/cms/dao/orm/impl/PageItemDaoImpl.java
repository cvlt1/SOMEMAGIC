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

import com.itacademy.jd2.yi.cms.dao.api.IPageItemDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageItemFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.ContentItem_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.PageItem;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.PageItem_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Page_;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site_;

@Repository
public class PageItemDaoImpl extends AbstractDaoImpl<IPageItem, Integer> implements IPageItemDao {

    protected PageItemDaoImpl() {
        super (PageItem.class);
    }
    
	@Override
	public IPageItem createEntity() {
		final PageItem pageItem = new PageItem();
		return pageItem;
	}

	@Override
		public List<IPageItem> find(PageItemFilter filter) {
			 final EntityManager em = getEntityManager();
		        final CriteriaBuilder cb = em.getCriteriaBuilder();
		        final CriteriaQuery<IPageItem> cq = cb.createQuery(IPageItem.class);
		        final Root<PageItem> from = cq.from(PageItem.class);
		        cq.select(from);

		        from.fetch(PageItem_.contentItem, JoinType.LEFT);

		        applyFilter(filter, cb, cq, from);

		        // set sort params
		        if (filter.getSortColumn() != null) {
		            final Path<?> expression = getSortPath(from, filter.getSortColumn());
		            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		        }

		        final TypedQuery<IPageItem> q = em.createQuery(cq);
		        setPaging(filter, q);
		        return q.getResultList();
	}
	
    @Override
    public IPageItem getFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();

        final CriteriaQuery<IPageItem> cq = cb.createQuery(IPageItem.class); // define returning result
        final Root<PageItem> from = cq.from(PageItem.class); // define table for select

        cq.select(from); // define what need to be selected

        from.fetch(PageItem_.contentItem, JoinType.LEFT);
        from.fetch(PageItem_.page, JoinType.LEFT);
        cq.distinct(true); // to avoid duplicate rows in result

        // .. where id=...
        cq.where(cb.equal(from.get(PageItem_.id), id)); // where id=?

        final TypedQuery<IPageItem> q = em.createQuery(cq);

        return getSingleResult(q);
    }

	@Override
	public long getCount(PageItemFilter filter) {
		final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<PageItem> from = cq.from(PageItem.class);
        cq.select(cb.count(from));
        applyFilter(filter, cb, cq, from);
        final TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}

	@Override
	public void save(IPageItem... entities) {
		throw new RuntimeException("not implemented1");
		
	}
	
	private void applyFilter(final PageItemFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
            final Root<PageItem> from) {
        final List<Predicate> ands = new ArrayList<>();

        final Integer position = filter.getPosition();
        if (!StringUtils.isEmpty(position)) {
            ands.add(cb.equal(from.get(PageItem_.position), position));
        }


        Integer pageId = filter.getPageId();
		if (pageId!=null) {
            ands.add(cb.equal(from.get(PageItem_.page).get(Page_.id), pageId));
        }
        
        if (!ands.isEmpty()) {
            cq.where(cb.and(ands.toArray(new Predicate[0])));
        }
    }
	
	
    private Path<?> getSortPath(final Root<PageItem> from, final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return from.get(PageItem_.created);
        case "updated":
            return from.get(PageItem_.updated);
        case "id":
            return from.get(PageItem_.id);
        case "position":
            return from.get(PageItem_.position);
        case "content_item_id":
            return from.get(PageItem_.contentItem).get(ContentItem_.title);
        case "page_id":
            return from.get(PageItem_.page).get(Page_.path);
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }
	//TODO add save method
    
    



	

}