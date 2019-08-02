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

import com.itacademy.jd2.yi.cms.dao.api.ICssItemDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.CssItemFilter;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.CssItem;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.Site;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.SQLExecutionException;

	
	@Repository
	public class CssItemImpl extends AbstractDaoImpl<ICssItem, Integer> implements ICssItemDao {


	    
	    @Override
	    public ICssItem createEntity() {
	        return new CssItem();
	    }

	    @Override
	    public void insert(final ICssItem entity) {
	        try (Connection c = getConnection();
	                PreparedStatement pStmt = c.prepareStatement(String
	                        .format("insert into %s (content, created, updated, site_id) values(?,?,?,?)", getTableName()),
	                        Statement.RETURN_GENERATED_KEYS)) {
	            c.setAutoCommit(false);
	            try {
	                pStmt.setString(1, entity.getContent());
	                pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
	                pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
	                pStmt.setInt(4, entity.getSite().getId());

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
	    public void update(final ICssItem entity) {
	        try (Connection c = getConnection();
	                PreparedStatement pStmt = c.prepareStatement(
	                        String.format("update %s set content=?, updated=?, site_id=? where id=?", getTableName()))) {
	            c.setAutoCommit(false);
	            try {
	                pStmt.setString(1, entity.getContent());
	                pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
	                pStmt.setInt(3, entity.getSite().getId());
	                pStmt.setInt(4, entity.getId());
	                pStmt.executeUpdate();
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
	    protected ICssItem parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
	        final ICssItem entity = createEntity();
	        entity.setId((Integer) resultSet.getObject("id"));
	        entity.setContent(resultSet.getString("content"));
	        entity.setCreated(resultSet.getTimestamp("created"));
	        entity.setUpdated(resultSet.getTimestamp("updated"));

	        final Integer siteId = (Integer) resultSet.getObject("site_id");
	        if (siteId != null) {
	            final ISite site = new Site();
	            site.setId(siteId);
	            if (columns.contains("site_name")) {
	                site.setName(resultSet.getString("site_name"));
	            }
	            entity.setSite(site);
	        }
	        return entity;
	    }

	    @Override
	    public void deleteAll() {
	        try (Connection c = getConnection();
	                PreparedStatement deleteEngineRefsStmt = c.prepareStatement("delete from css_item_2_page");
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

//	    @Override
//	    public IModel getFullInfo(final Integer id) {
//	        final IModel model = get(id);
//	        final Set<IEngine> engines = engineDao.getByModel(id);
//	        model.setEngines(engines);
//
//	        model.setModelInfo(modelInfoDao.get(id));
//	        return model;
//	    }

	    @Override
	    protected String getTableName() {
	        return "css_item";
	    }

//	    private void updateEngines(final ICssItem entity, final Connection c) throws SQLException {
//	        // clear all existing records related to the current model
//	        final PreparedStatement deleteStmt = c.prepareStatement("delete from model_2_engine where model_id=?");
//	        deleteStmt.setInt(1, entity.getId());
//	        deleteStmt.executeUpdate();
//	        deleteStmt.close();
//
//	        if (entity.getEngines().isEmpty()) {
//	            return;
//	        }
//
//	        // insert actual list of records using 'batch'
//	        final PreparedStatement pStmt = c
//	                .prepareStatement("insert into model_2_engine (model_id, engine_id) values(?,?)");
//
//	        for (final IEngine e : entity.getEngines()) {
//	            pStmt.setInt(1, entity.getId());
//	            pStmt.setInt(2, e.getId());
//	            pStmt.addBatch();
//	        }
//	        pStmt.executeBatch();
//	        pStmt.close();
//	    }

	    @Override
	    public List<ICssItem> find(final CssItemFilter filter) {
	        final StringBuilder sql;
	        if (filter.getFetchSite()) {
	            sql = new StringBuilder(String.format("select css_item.*, site.name as site_name from %s", getTableName()));
	        } else {
	            sql = new StringBuilder(String.format("select model.* from %s", getTableName()));
	        }
	        appendJOINs(sql, filter);
	        appendWHEREs(sql, filter);
	        appendSort(filter, sql);
	        appendPaging(filter, sql);
	        return executeFindQueryWithCustomSelect(sql.toString());
	    }

	    @Override
	    public long getCount(final CssItemFilter filter) {
	        final StringBuilder sql = new StringBuilder("");
	        appendJOINs(sql, filter);
	        appendWHEREs(sql, filter);
	        return executeCountQuery(sql.toString());
	    }

	    private void appendJOINs(final StringBuilder sb, final CssItemFilter filter) {
	        if (filter.getFetchSite()) {
	            sb.append(" join site on (site.id=css_item.site_id) ");
	        }
	    }

	    private void appendWHEREs(final StringBuilder sb, final CssItemFilter filter) {
	        // nothing yet
	}

}
