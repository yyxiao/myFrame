package com.psy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.psy.dao.EntityDao;

public class EntityDaoImpl extends HibernateDaoSupport implements EntityDao{
	public List<Object> createQuery(final String queryString) {
		return (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Query query = session.createQuery(queryString);
						List<Object> rows = query.list();
						return rows;
					}
				});
	}
	public Object save(final Object model) {
		return  getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						session.save(model);
						return null;
					}
				});
	}
	public void update(final Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.update(model);
				return null;
			}
		});
	}
	public void delete(final Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.delete(model);
				return null;
			}
		});
	}
}
