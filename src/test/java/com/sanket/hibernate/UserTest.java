package com.sanket.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.sanket.hibernate.pojos.ShopUser;
import com.sanket.hibernate.pojos.ShopUserDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class UserTest extends AbstractTransactionalJUnit4SpringContextTests {
	// private final org.slf4j.Logger logger = org.slf4j.LoggerFactory
	// .getLogger(UserTest.class);

	protected static int SIZE = 2;
	protected static Integer ID = new Integer(1);

	protected static String USER_NAME = "sanket";
	protected static String PASS_WORD = "PASSWORD";
	protected static String COUNTRY = "INDIA";
	protected static String EMAIL = "sanket@gmail.com";
	protected static String MOBILE = "9833526863";

	@Autowired
	protected SessionFactory sessionFactory = null;

	@Autowired
	protected HibernateTemplate hibernateTemplate = null;

	// @Test
	public void insertUser() {
		ShopUser user = new ShopUser(USER_NAME, PASS_WORD);
		ShopUserDetails details = new ShopUserDetails(COUNTRY, MOBILE, EMAIL);
		user.setShopUserDetail(details);
		details.setShopUser(user);
		hibernateTemplate.saveOrUpdate(user);
	}

	@Test
	@SuppressWarnings(value = { "unchecked" })
	public void selectUsers() {

		List<ShopUser> users = hibernateTemplate.find(
				"from ShopUser u where u.userName =?", USER_NAME);
		if (users != null) {
			for (ShopUser u : users)
				logger.info("User is :" + u);
		}
	}

	public void deleteUser() {

		ShopUser user = hibernateTemplate.get(ShopUser.class, 1);
		if (user != null) {
			logger.info(">>>>>>Deleting the user");
			hibernateTemplate.delete(user);
		}
	}

	// @Test
	public void testDatabase() {
		System.out.println("Test run complete");
		ShopUser user = new ShopUser(USER_NAME, PASS_WORD);
		ShopUserDetails details = new ShopUserDetails(COUNTRY, MOBILE, EMAIL);
		user.setShopUserDetail(details);
		details.setShopUser(user);

		Transaction txn = null;
		try {
			txn = getCurrentSession().beginTransaction();
			Serializable s = sessionFactory.getCurrentSession().save(user);
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

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
