package com.itacademy.jd2.yi.cms.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.IPageDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.Page;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.Site;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.Template;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.SQLExecutionException;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;;

@Repository
public class PageDaoImpl extends AbstractDaoImpl<IPage, Integer> implements IPageDao {


    
    @Override
    public IPage createEntity() {
        return new Page();
    }

    @Override
    public void insert(final IPage entity) {
        try (Connection c = getConnection();
                PreparedStatement pStmt = c.prepareStatement(String
                        .format("insert into %s (site_id, parent_id, template_id, path, "
                        		+ "status, creator, title, created, updated, id) values(?,?,?,?,?,?,?,?,?,?)", getTableName()),
                        Statement.RETURN_GENERATED_KEYS)) {
            c.setAutoCommit(false);
            try {
                pStmt.setInt(1, entity.getSiteId().getId());
                pStmt.setInt(2, entity.getParentId());
                pStmt.setInt(3, entity.getTemplateId().getId());
                pStmt.setString(4, entity.getPath());
                pStmt.setString(5, entity.getStatus().name());
                pStmt.setInt(6, entity.getCreator().getId());
                pStmt.setString(7, entity.getTitle());
                pStmt.setObject(8, entity.getCreated(), Types.TIMESTAMP);
                pStmt.setObject(9, entity.getUpdated(), Types.TIMESTAMP);
                pStmt.setInt(10, entity.getId());

                pStmt.executeUpdate();

                final ResultSet rs = pStmt.getGeneratedKeys();
                rs.next();
                final int id = rs.getInt("id");

                rs.close();
                entity.setId(id);
                // the same should be done in 'update' DAO method
                c.commit();
            } catch (final Exception e) {
                c.rollback();
                throw new RuntimeException(e);
            }

        } catch (final SQLException e) {
            throw new SQLExecutionException(e);
        }
    }

    @Override
    public void update(final IPage entity) {
        try (Connection c = getConnection();
                PreparedStatement pStmt = c.prepareStatement(
                        String.format("update %s set site_id=?, parent_id=?, template_id=?, path=?, status=?, creator=?, title=?, updated=? where id=?", getTableName()))) {
            c.setAutoCommit(false);
            try {
                pStmt.setInt(1, entity.getSiteId().getId());
                pStmt.setObject(2, entity.getParentId());
                pStmt.setInt(3, entity.getTemplateId().getId());
                pStmt.setString(4, entity.getPath());
                pStmt.setString(5, entity.getStatus().name());
                pStmt.setInt(6, entity.getCreator().getId());
                pStmt.setString(7, entity.getTitle());
                pStmt.setObject(8, entity.getUpdated(), Types.TIMESTAMP);
                pStmt.setInt(9, entity.getId());
                // the same should be done in 'update' DAO method
                c.commit();
            } catch (final Exception e) {
                c.rollback();
                throw new RuntimeException(e);
            }

        } catch (final SQLException e) {
            throw new SQLExecutionException(e);
        }
    }

    @Override
    protected IPage parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
        final IPage entity = createEntity();
        entity.setId((Integer) resultSet.getObject("id"));
        entity.setParentId((Integer) resultSet.getObject("parent_id"));
        entity.setPath(resultSet.getString("path"));
        entity.setStatus(PageStatus.valueOf(resultSet.getString("status")));
        entity.setTitle(resultSet.getString("title"));
        entity.setCreated(resultSet.getTimestamp("created"));
        entity.setUpdated(resultSet.getTimestamp("updated"));

        final Integer siteId = (Integer) resultSet.getObject("site_id");
        if (siteId != null) {
            final ISite site = new Site();
            site.setId(siteId);
            if (columns.contains("site_name")) {
                site.setSiteName(resultSet.getString("site_name"));
            }
            entity.setSiteId(site);
        }
//        final Integer parentId = (Integer) resultSet.getObject("parent_id");
//        if (parentId != null) {
//            final IPage page = new Page();
//            page.setId(parentId);
//            if (columns.contains("parent_id")) {
//            	page.setId(resultSet.getInt("parent_id"));
//            }
//            entity.setParentId(page);
//        }
        final Integer templateId = (Integer) resultSet.getObject("template_id");
        if (templateId != null) {
            final ITemplate template = new Template();
            template.setId(templateId);
            if (columns.contains("template_id")) {
            	template.setId(resultSet.getInt("template_id"));
            }
            entity.setTemplateId(template);
        }
        final Integer creator = (Integer) resultSet.getObject("creator");
        if (creator != null) {
            final IUserAccount userAccount = new UserAccount();
            userAccount.setId(creator);
            if (columns.contains("template_id")) {
            	userAccount.setId(resultSet.getInt("creator"));
            }
            entity.setCreator(userAccount);
        }
        return entity;
    }

    @Override
    public void deleteAll() {
        try (Connection c = getConnection();
                PreparedStatement deleteEngineRefsStmt = c.prepareStatement("delete from page");
                PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
            c.setAutoCommit(false);
            try {
                deleteEngineRefsStmt.executeUpdate();
                deleteAllStmt.executeUpdate();

                deleteEngineRefsStmt.close();
                deleteAllStmt.close();

                c.commit();
            } catch (final Exception e) {
                c.rollback();
                throw new RuntimeException(e);
            }

        } catch (final SQLException e) {
            throw new SQLExecutionException(e);
        }

    }

    @Override
    protected String getTableName() {
        return "page";
    }

	@Override
	public void save(IPage... entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (IPage entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(String.format(
							"insert into %s (site_id, parent_id, template_id, path, "
	                        		+ "status, creator, title, created, updated) values(?,?,?,?,?,?,?,?,?)",
							getTableName()), Statement.RETURN_GENERATED_KEYS);

	                pStmt.setInt(1, entity.getSiteId().getId());
	                pStmt.setObject(2, entity.getParentId());
	                pStmt.setInt(3, entity.getTemplateId().getId());
	                pStmt.setString(4, entity.getPath());
	                pStmt.setString(5, entity.getStatus().name());
	                pStmt.setInt(6, entity.getCreator().getId());
	                pStmt.setString(7, entity.getTitle());
	                pStmt.setObject(8, entity.getCreated(), Types.TIMESTAMP);
	                pStmt.setObject(9, entity.getUpdated(), Types.TIMESTAMP);

					pStmt.executeUpdate();

					final ResultSet rs = pStmt.getGeneratedKeys();
					rs.next();
					final int id = rs.getInt("id");

					rs.close();
					pStmt.close();
					entity.setId(id);
				}

				// the same should be done in 'update' DAO method
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}
//    @Override
//    public List<IPage> find(final PageFilter filter) {
//        final StringBuilder sql;
//        if (filter.getFetchPage()) {
//        	// TODO finish this shit
//            sql = new StringBuilder(String.format("select page.*, site.name as site_name from %s", getTableName()));
//        } else {
//            sql = new StringBuilder(String.format("select page.* from %s", getTableName()));
//        }
//        appendJOINs(sql, filter);
//        appendWHEREs(sql, filter);
//        appendSort(filter, sql);
//        appendPaging(filter, sql);
//        return executeFindQueryWithCustomSelect(sql.toString());
//    }
	
	@Override
	public List<IPage> find(PageFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

    @Override
    public long getCount(final PageFilter filter) {
        final StringBuilder sql = new StringBuilder("");
        appendJOINs(sql, filter);
        appendWHEREs(sql, filter);
        return executeCountQuery(sql.toString());
    }

    private void appendJOINs(final StringBuilder sb, final PageFilter filter) {
        if (filter.getFetchPage()) {
            sb.append(" join site on (site.id=css_item.site_id) ");
        }
    }

    private void appendWHEREs(final StringBuilder sb, final PageFilter filter) {
        // nothing yet
}



}
