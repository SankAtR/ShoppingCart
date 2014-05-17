package com.sanket.hibernate.daoarchitecture;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class HibernateCustomBeanFactory implements ApplicationContextAware {
	private static ApplicationContext applicationCtxt;

	@Override
	public void setApplicationContext(ApplicationContext applicationCtxt) throws BeansException {
		HibernateCustomBeanFactory.applicationCtxt = applicationCtxt;
	}

	public static boolean containsBean(String beanName) {
		return applicationCtxt.containsBean(beanName);
	}

	public static <T> T getHibernateBean(String beanName, Class<T> clazz) {
		return applicationCtxt.getBean(beanName, clazz);
	}
}
