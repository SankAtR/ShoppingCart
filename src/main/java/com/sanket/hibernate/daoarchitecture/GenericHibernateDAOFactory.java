package com.sanket.hibernate.daoarchitecture;

public class GenericHibernateDAOFactory {
	public static <T> IGenericHibernateDAO<T> getDAO(Class<T> baseClass) {
		String daoName = new StringBuilder().append(baseClass.getSimpleName()).append("DAO").toString();
		@SuppressWarnings("unchecked")
		IGenericHibernateDAO<T> dao = HibernateCustomBeanFactory.getHibernateBean(daoName, IGenericHibernateDAO.class);
		return dao;
	}
}
