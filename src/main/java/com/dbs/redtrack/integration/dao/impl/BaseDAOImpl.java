package com.dbs.redtrack.integration.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.integration.dao.BaseDAO;

@Repository
public abstract class BaseDAOImpl implements BaseDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Object object) {
		getEm().persist(object);
	}

	@Override
	public void remove(Object object) {
		this.getEm().remove(object);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object find(Class type,Object object) {
		return this.getEm().find(type,object);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
