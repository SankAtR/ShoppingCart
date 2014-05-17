package com.sanket.hibernate.daoarchitecture;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(value = "prototype")
public class GenericHibernateDAOImpl<T> implements IGenericHibernateDAO<T> {
	private Class<T> baseClass;

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public GenericHibernateDAOImpl() {
		super();
	}

	@Override
	public String create(T t) {
		_assertBaseClassSetup(this);

		try {
			Serializable s = hibernateTemplate.save(t);
			return (s == null ? "" : s.toString());
		} catch (HibernateException e) {
			throw new Error(e);
		} finally {

			getCurrentSession().flush();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public T retrieveUsingID(String rowID) {
		_assertBaseClassSetup(this);
		List<T> entities = hibernateTemplate.find(new StringBuilder("from ")
				.append(baseClass.getSimpleName())
				.append(" as e where e.rowId='").append(rowID).append("'")
				.toString());
		try {
			return (entities == null ? null : entities.get(0));
		} catch (Exception e) {
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

		String query = new StringBuilder("from ")
				.append(baseClass.getSimpleName()).append(" as e where (")
				.append(q.toString()).append(")").toString();
		return hibernateTemplate.find(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> retrieve() {
		_assertBaseClassSetup(this);
		return hibernateTemplate.find(new StringBuilder("from ").append(
				baseClass.getSimpleName()).toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<T> retrieve(String query) {
		_assertBaseClassSetup(this);
		return hibernateTemplate.find(query);
	}

	@Override
	public T retrieveFirst(String query) {
		List<T> list = retrieve(query);
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	@Override
	public Criteria formCriteria() {
		_assertBaseClassSetup(this);
		return (Criteria) DetachedCriteria.forClass(baseClass);
	}

	@Override
	public Query formQuery(String query) {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(query);
	}

	@Override
	public void update(T t) {
		_assertBaseClassSetup(this);
		try {
			hibernateTemplate.update(t);
		} catch (HibernateException e) {
			throw new Error(e);
		}
	}

	@Override
	public void bulkUpdate(String query) {
		try {
			hibernateTemplate.bulkUpdate(query);
		} catch (HibernateException e) {
			throw new Error(e);
		}
	}

	@Override
	public void saveOrUpdate(T t) {
		_assertBaseClassSetup(this);
		try {
			hibernateTemplate.saveOrUpdate(t);
		} catch (HibernateException e) {
			throw new Error(e);
		}
	}

	@Override
	public void merge(T t) {
		_assertBaseClassSetup(this);
		try {
			hibernateTemplate.merge(t);
		} catch (HibernateException e) {
			throw new Error(e);
		}
	}

	@Override
	public void delete(T t) {
		_assertBaseClassSetup(this);
		if (null != t) {
			try {
				hibernateTemplate.delete(t);
			} catch (HibernateException e) {
				throw new Error(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(String id) {
		_assertBaseClassSetup(this);
		T t = (T) hibernateTemplate.load(baseClass, id);

		if (null != t) {
			try {
				hibernateTemplate.delete(t);
			} catch (HibernateException e) {
				throw new Error(e);
			}
		}
	}

	@Override
	public void bulkDelete(String query) {
		Transaction txn = null;
		try {
			txn = getCurrentSession().beginTransaction();
			getCurrentSession().createQuery(query).executeUpdate();
			txn.commit();
		} catch (HibernateException e) {
			throw new Error(e);
		} finally {
			if (!txn.wasCommitted()) {
				txn.rollback();
			}
			getCurrentSession().flush();
		}
	}

	private static <T> void _assertBaseClassSetup(GenericHibernateDAOImpl<T> dao) {
		if (dao.baseClass == null)
			throw new RuntimeException(
					"Base class for the DAO has not been specified yet. Please use setBaseClass to specify the base POJO class for this DAO.");
	}

	public Class<T> getBaseClass() {
		return baseClass;
	}

	public IGenericHibernateDAO<T> setBaseClass(Class<T> baseClass) {
		this.baseClass = baseClass;
		return this;
	}

	@Override
	public Session getCurrentSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	@Override
	public SessionFactory getSessionFactory() {
		return hibernateTemplate.getSessionFactory();
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate.setSessionFactory(sessionFactory);
	}
}