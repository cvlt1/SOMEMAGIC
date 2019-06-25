package com.itacademy.jd2.yi.cms.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.IUserAccountDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.SQLExecutionException;

public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public void insert(final IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(
				String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()), true) {
			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
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

	@Override
	public void update(final IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(
				String.format("update %s set name=?, updated=? where id=?", getTableName())) {
			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "user_account";
	}

	@Override
	protected IUserAccount parseRow(final ResultSet resultSet) throws SQLException {
		final IUserAccount entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public void save(IUserAccount... entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (IUserAccount entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(
							String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()),
							Statement.RETURN_GENERATED_KEYS);

					pStmt.setString(1, entity.getName());
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
	public List<IUserAccount> find(final UserAccountFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(final UserAccountFilter filter) {
		return executeCountQuery("");
	}
}