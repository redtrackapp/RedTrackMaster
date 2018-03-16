package com.dbs.redtrack.integration.dao;

public interface BaseDAO {
	
	public void save(Object object);
	
	public void remove(Object object); 
	
	@SuppressWarnings("rawtypes")
	public Object find(Class type,Object object);
}
