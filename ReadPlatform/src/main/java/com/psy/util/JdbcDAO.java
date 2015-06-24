/**
 * JdbcDAO.java
 * com.xiao.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.util;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * ClassName:JdbcDAO
 *
 * TODO(JdbcDAO)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年4月17日 下午2:05:14	
 *
 * @class com.xiao.util.JdbcDAO
 *
 */ 
public class JdbcDAO {
	JdbcTemplate jdbcTemplate = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map queryForMap(String sql) {
		return jdbcTemplate.queryForMap(sql);
	}

	public Map findForMap(String sql) {
		return queryForMap(sql);
	}

	public Map queryForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForMap(sql, args);
	}

	public List findForList(String sql) {
		return queryForList(sql);
	}

	public List queryForList(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	public List queryForList(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	public int queryForInt(String sql) {
		return jdbcTemplate.queryForInt(sql);
	}

	public int queryForInt(String sql, Object[] args) {
		return jdbcTemplate.queryForInt(sql, args);
	}

	public long queryForLong(String sql) {
		return jdbcTemplate.queryForLong(sql);
	}

	public long queryForLong(String sql, Object[] args) {
		return jdbcTemplate.queryForLong(sql, args);
	}

	public void execute(String sql) {
		jdbcTemplate.execute(sql);
	}

	public int update(String sql) {
		return jdbcTemplate.update(sql);
	}

	public int update(String sql, Object[] args) {
		return jdbcTemplate.update(sql, args);
	}

	public int callProcedure(String procedureName) {
		final String sql = "{call " + procedureName + "}";
		Object obj = jdbcTemplate.execute(sql, new CallableStatementCallback() {
			public Object doInCallableStatement(java.sql.CallableStatement cs) throws java.sql.SQLException, DataAccessException {
				// cs.setInt(1, userId);
				// cs.registerOutParameter(2, Types.INTEGER);
				cs.execute();
				return 0;
			}
		});
		return ((Integer) obj).intValue();
	}
}
