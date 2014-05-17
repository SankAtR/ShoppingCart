package com.sanket.hibernate.daoarchitecture;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(value = "prototype")
public class GenericHibernateDAOImpl<T> implements IGenericHibernateDAO<T> {
	private Class<T> baseClass;
	private SessionFactory sessionFactory;

	public GenericHibernateDAOImpl() {
		super();
	}

	@Override
	public String create(T t) {
		_assertBaseClassSetup(this);
		Transaction txn = null;
		try {
			txn = getCurrentSession().beginTransaction();
			Serializable s = sessionFactory.getCurrentSession().save(t);
			txn.commit();
			return (s == null ? "" : s.toString());
		}
		catch (HibernateException e) {
			throw new Error(e);
		}
		finally {
			if (!txn.wasCommitted()) {
				txn.rollback();
			}
			getCurrentSession().flush();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T retrieveUsingID(String rowID) {
		_assertBaseClassSetup(this);
		List<T> entities = sessionFactory.getCurrentSession().createQuery(new StringBuilder("from ").append(baseClass.getSimpleName()).append(" as e where e.rowId='").append(rowID).append("'").toString()).list();
		try {
			return (entities == null ? null : entities.get(0));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<T> retrieve(List<String> rowIDs) {
		_assertBaseClassSetup(this);
		StringBuilder q = new StringBuilder();
		for (String rowID : rowIDs) {
			if (!q.toString().equals("")) {
				q.append(" or ");
			}
			q.append("e.rowId='").append(rowID).append("'");
		}

		String query = new StringBuilder("from ").append(baseClass.getSimpleName()).append(" as e where (").append(q.toString()).append(")").toString();
		return sessionFactory.getCurrentSession().createQuery(query).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> retrieve() {
		_assertBaseClassSetup(this);
		return sessionFactory.getCurrentSession().createQuery(new StringBuilder("from ").append(baseClass.getSimpleName()).toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<T> retrieve(String query) {
		_assertBaseClassSetup(this);
		return sessionFactory.getCurrentSession().createQuery(query).list();
	}

	@Override
	public T retrieveFirst(String query) {
		List<T> list = retrieve(query);
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	@Override
	public Criteria formCriteria() {
		_assertBaseClassSetup(this);
		return sessionFactory.getCurrentSession().createCriteria(baseClass);
	}

	@Override
	public Query formQuery(String query) {
		return sessionFactory.getCurrentSession().createQuery(query);
	}

	@Override
	public void update(T t) {
		_assertBaseClassSetup(this);
		Transaction txn = null;
		try {
			txn = getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().update(t);
			txn.commit();
		}
		catch (HibernateException e) {
			throw new Error(e);
		}
		finally {
			if (!txn.wasCommitted()) {
				txn.rollback();
			}
			getCurrentSession().flush();
		}
	}

	@Override
	public void bulkUpdate(String query) {
		Transaction txn = null;
		try {
			txn = getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().createQuery(query).executeUpdate();
			txn.commit();
		}
		catch (HibernateException e) {
			throw new Error(e);
		}
		finally {
			if (!txn.wasCommitted()) {
				txn.rollback();
			}
			getCurrentSession().flush();
		}
	}

	@Override
	public void saveOrUpdate(T t) {
		_assertBaseClassSetup(this);
		Transaction txn = null;
		try {
			txn = getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().saveOrUpdate(t);
			
			
			
			
			txn.commit();
		}
		catch (HibernateException e) {
			throw new Error(e);
		}
		finally {
			if (!txn.wasCommitted()) {
				txn.rollback();
			}
			getCurrentSession().flush();
		}
	}

	@Override
	public void merge(T t) {
		_assertBaseClassSetup(this);
		Transaction txn = null;
		try {
			txn = getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().merge(t);
			txn.commit();
		}
		catch (HibernateException e) {
			throw new Error(e);
		}
		finally {
			if (!txn.wasCommitted()) {
				txn.rollback();
			}
			getCurrentSession().flush();
		}
	}

	@Override
	public void delete(T t) {
		_assertBaseClassSetup(this);

		if (null != t) {
			Transaction txn = null;
			try {
				txn = getCurrentSession().beginTransaction();
				sessionFactory.getCurrentSession().delete(t);
				txn.commit();
			}
			catch (HibernateException e) {
				throw new Error(e);
			}
			finally {
				if (!txn.wasCommitted()) {
					txn.rollback();
				}
				getCurrentSession().flush();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(String id) {
		_assertBaseClassSetup(this);
		T t = (T) sessionFactory.getCurrentSession().load(baseClass, id);

		if (null != t) {
			Transaction txn = null;
			try {
				txn = getCurrentSession().beginTransaction();
				sessionFactory.getCurrentSession().delete(t);
				txn.commit();
			}
			catch (HibernateException e) {
				throw new Error(e);
			}
			finally {
				if (!txn.wasCommitted()) {
					txn.rollback();
				}
				getCurrentSession().flush();
			}
		}
	}

	@Override
	public void bulkDelete(String query) {
		Transaction txn = null;
		try {
			txn = getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().createQuery(query).executeUpdate();
			txn.commit();
		}
		catch (HibernateException e) {
			throw new Error(e);
		}
		finally {
			if (!txn.wasCommitted()) {
				txn.rollback();
			}
			getCurrentSession().flush();
		}
	}

	private static <T> void _assertBaseClassSetup(GenericHibernateDAOImpl<T> dao) {
		if (dao.baseClass == null)
			throw new RuntimeException("Base class for the DAO has not been specified yet. Please use setBaseClass to specify the base POJO class for this DAO.");
	}

	public Class<T> getBaseClass() {
		return baseClass;
	}

	public IGenericHibernateDAO<T> setBaseClass(Class<T> baseClass) {
		this.baseClass = baseClass;
		return this;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
