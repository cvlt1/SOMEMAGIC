package com.itacademy.jd2.yi.cms.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.ISiteDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.Site;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.SQLExecutionException;

@Repository
public class SiteDaoImpl extends AbstractDaoImpl<ISite, Integer> implements ISiteDao {

	@Override
	public ISite createEntity() {
		return new Site();
	}

	@Override
	public void update(final ISite entity) {
		executeStatement(new PreparedStatementAction<ISite>(String.format(
				"update %s set name=?, basepath=?, updated=? where id=?", getTableName())) {
			@Override
			public ISite doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getBasepath());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(final ISite entity) {
		executeStatement(new PreparedStatementAction<ISite>(String.format(
				"insert into %s (name, basepath, created, updated) values(?,?,?,?)",
				getTableName()), true) {
			@Override
			public ISite doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getBasepath());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

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

	protected ISite parseRow(final ResultSet resultSet) throws SQLException {
		final ISite entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setBasepath(resultSet.getString("basepath"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public void save(ISite... entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (ISite entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(String.format(
							"insert into %s (name, basepath, created, updated) values(?,?,?,?)",
							getTableName()), Statement.RETURN_GENERATED_KEYS);

					pStmt.setString(1, entity.getName());
					pStmt.setString(2, entity.getBasepath());
					pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
					pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);

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
		return "site";
	}

	@Override
	public List<ISite> find(SiteFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(SiteFilter filter) {
		return executeCountQuery("");
	}

}
