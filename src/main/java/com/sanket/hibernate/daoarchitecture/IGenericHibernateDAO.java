package com.sanket.hibernate.daoarchitecture;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface IGenericHibernateDAO<T> {
	public String create(T t);

	public List<T> retrieve();

	public T retrieveUsingID(String rowID);

	public List<T> retrieve(List<String> rowIDs);

	public List<T> retrieve(String query);

	public T retrieveFirst(String query);

	public Query formQuery(String query);

	public Criteria formCriteria();

	public void update(T t);

	public void bulkUpdate(String query);

	public void saveOrUpdate(T t);

	public void delete(T t);

	public void delete(String id);

	public void bulkDelete(String query);

	public Class<T> getBaseClass();

	public IGenericHibernateDAO<T> setBaseClass(Class<T> baseClass);

	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);

	void merge(T t);

	public Session getCurrentSession();
}
