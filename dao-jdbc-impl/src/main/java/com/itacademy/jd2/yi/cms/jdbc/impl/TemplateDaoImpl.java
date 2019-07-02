package com.itacademy.jd2.yi.cms.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.ITemplateDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.Template;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.SQLExecutionException;
@Repository
public class TemplateDaoImpl extends AbstractDaoImpl<ITemplate, Integer> implements ITemplateDao {

	@Override
	public ITemplate createEntity() {
		return new Template();
	}

	@Override
	public void update(final ITemplate entity) {
		executeStatement(new PreparedStatementAction<ITemplate>(String.format(
				"update %s set jsp_path=?, updated=? where id=?", getTableName())) {
			@Override
			public ITemplate doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getJspPath());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(final ITemplate entity) {
		executeStatement(new PreparedStatementAction<ITemplate>(String.format(
				"insert into %s (jsp_path, created, updated) values(?,?,?)",
				getTableName()), true) {
			@Override
			public ITemplate doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getJspPath());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});

	}

	protected ITemplate parseRow(final ResultSet resultSet) throws SQLException {
		final ITemplate entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setJspPath(resultSet.getString("jsp_path"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public void save(ITemplate... entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (ITemplate entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(String.format(
							"insert into %s (jsp_path, created, updated) values(?,?,?)",
							getTableName()), Statement.RETURN_GENERATED_KEYS);

					pStmt.setString(1, entity.getJspPath());
					pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
					pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

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

	@Override
	protected String getTableName() {
		return "template";
	}

	@Override
	public List<ITemplate> find(TemplateFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(TemplateFilter filter) {
		return executeCountQuery("");
	}

}

