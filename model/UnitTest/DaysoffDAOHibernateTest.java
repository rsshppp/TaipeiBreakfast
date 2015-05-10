package UnitTest;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import model.bean.DaysoffBean;
import model.bean.ShopBean;
import model.dao.DaysoffDAO;
import model.dao.OrderSumDAO;
import model.dao.imp.OrderSumDAOHibernate;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaysoffDAOHibernateTest {
	private ConfigurableApplicationContext context;
	private SessionFactory sessionFactory;
	private DaysoffDAO dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		dao =  (DaysoffDAO) context.getBean("daysoffDAO");
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		sessionFactory.getCurrentSession().getTransaction().commit();
		context.close();
		System.out.println("done");
	}

	@Test
	public void testInsertDaysoff() {
		ShopBean bean = new ShopBean();
		bean.setShopID(3);
		java.sql.Date daysoff = new java.sql.Date(new java.util.Date().getTime());
		DaysoffBean dbean = new DaysoffBean();
		dbean.setShopID(bean.getShopID());
		dbean.setDaysoff(daysoff);
		System.out.println("dbean="+dbean);
		boolean b = dao.insertDaysoff(dbean);
		System.out.println("testInsertDaysoff="+b);
	}

	@Test
	public void testDeleteDaysoff() {
		DaysoffBean dbean = new DaysoffBean();
		dbean.setShopID(3);
		java.sql.Date date = java.sql.Date.valueOf("2015-05-06");
		dbean.setDaysoff(date);
		boolean b = dao.deleteDaysoff(dbean);
		System.out.println("testDeleteDaysoff="+b);
		
	}

	@Test
	public void testQueryDaysoff() {
		ShopBean bean = new ShopBean();
		bean.setShopID(3);
		Iterator iterator= dao.queryDaysoff(bean).iterator();
		while(iterator.hasNext()){
			DaysoffBean dbean = (DaysoffBean) iterator.next();
			System.out.println("testQueryDaysoff="+dbean);
		}
	}

}
