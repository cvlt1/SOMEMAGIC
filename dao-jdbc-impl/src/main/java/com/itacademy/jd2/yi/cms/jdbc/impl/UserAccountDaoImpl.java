package com.itacademy.jd2.yi.cms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.IUserAccountDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.yi.cms.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.yi.cms.jdbc.impl.util.PreparedStatementAction;


public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public void update(IUserAccount entity) {
		throw new RuntimeException("not implemented");

	}

	@Override
	public void insert(IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(
				String.format("insert into %s (name, eamil, password, role, status, created, updated) values(?,?,?,?,?,?,?)",
						getTableName()),
				true) {
			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getEmail());
				pStmt.setString(3, entity.getPassword());
				pStmt.setString(4, entity.getRole());
				pStmt.setString(5, entity.getStatus());
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

	@Override
	protected String getTableName() {
		return "user_account";
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		throw new RuntimeException("not implemented");
	}

	@Override
	protected IUserAccount parseRow(final ResultSet resultSet) throws SQLException {
		final IUserAccount entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setEmail(resultSet.getString("email"));
		entity.setPassword(resultSet.getString("password"));
		entity.setRole(resultSet.getString("role"));
		entity.setStatus(resultSet.getString("status"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public void save(IUserAccount... entities) {
		// TODO Auto-generated method stub
		
	}


}

