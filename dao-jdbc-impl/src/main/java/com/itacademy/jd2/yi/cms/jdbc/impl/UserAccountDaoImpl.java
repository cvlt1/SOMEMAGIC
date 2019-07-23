package com.itacademy.jd2.yi.cms.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.IUserAccountDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.SQLExecutionException;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.StatementAction;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public void update(final IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(String.format(
				"update %s set name=?, email=?, password=?, role=?, status=?, updated=? where id=?", getTableName())) {
			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getEmail());
				pStmt.setString(3, entity.getPassword());
				pStmt.setString(4, entity.getRole().name());
				pStmt.setString(5, entity.getStatus().name());
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(7, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(final IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(String.format(
				"insert into %s (name, email, password, role, status, created, updated) values(?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getEmail());
				pStmt.setString(3, entity.getPassword());
				pStmt.setString(4, entity.getRole().name());
				pStmt.setString(5, entity.getStatus().name());
				pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);

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

	protected IUserAccount parseRow(final ResultSet resultSet) throws SQLException {
		final IUserAccount entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setEmail(resultSet.getString("email"));
		entity.setPassword(resultSet.getString("password"));
		entity.setRole(UserRole.valueOf(resultSet.getString("role")));
		entity.setStatus(UserStatus.valueOf(resultSet.getString("status")));
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
					PreparedStatement pStmt = c.prepareStatement(String.format(
							"insert into %s (name, email, password, role, status, created, updated) values(?,?,?,?,?,?,?)",
							getTableName()), Statement.RETURN_GENERATED_KEYS);

					pStmt.setString(1, entity.getName());
					pStmt.setString(2, entity.getEmail());
					pStmt.setString(3, entity.getPassword());
					pStmt.setString(4, entity.getRole().name());
					pStmt.setString(5, entity.getStatus().name());
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
		return "user_account";
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public IUserAccount findByNickname(String name) {
		StatementAction<IUserAccount> action = (statement) -> {
			statement.executeQuery(String.format("select * from user_account where name ='%s'", name));

			final ResultSet resultSet = statement.getResultSet();

			final boolean hasNext = resultSet.next();
			IUserAccount result = null;
			if (hasNext) {
				result = parseRow(resultSet);
			}

			resultSet.close();
			return result;
		};
		IUserAccount entityById = executeStatement(action);
		return entityById;

	}

}