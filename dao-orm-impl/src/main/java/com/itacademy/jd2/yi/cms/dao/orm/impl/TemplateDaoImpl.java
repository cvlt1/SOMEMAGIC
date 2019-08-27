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

import com.itacademy.jd2.yi.cms.dao.api.ITemplateDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Template;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Template_;



@Repository
public class TemplateDaoImpl extends AbstractDaoImpl<ITemplate, Integer> implements ITemplateDao {

    protected TemplateDaoImpl() {
        super(Template.class);
    }

    @Override
    public ITemplate createEntity() {
        final Template template = new Template();
        return template;
    }

	@Override
	public void save(ITemplate... entities) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public List<ITemplate> find(TemplateFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ITemplate> cq = cb.createQuery(ITemplate.class);

		final Root<Template> from = cq.from(Template.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Template, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
			// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
			// by
			// column_name
			// order
		}

		final TypedQuery<ITemplate> q = em.createQuery(cq);
		setPaging(filter, q);

		return q.getResultList();
	}
	
	private SingularAttribute<? super Template, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Template_.created;
		case "updated":
			return Template_.updated;
		case "id":
			return Template_.id;
		case "name":
			return Template_.viewName;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(TemplateFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Template> from = cq.from(Template.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}