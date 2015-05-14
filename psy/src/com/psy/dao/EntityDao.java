package com.psy.dao;

import java.util.List;

public interface EntityDao {
	public List<Object> createQuery(final String queryString);
	public Object save(final Object model);
	public void update(final Object model);
	public void delete(final Object model);
}
