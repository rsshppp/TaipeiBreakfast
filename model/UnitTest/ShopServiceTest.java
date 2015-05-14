package UnitTest.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;

import model.bean.DaysoffBean;
import model.bean.ShopBean;
import model.dao.ShopDAO;
import model.service.ShopService;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShopServiceTest {
	private ConfigurableApplicationContext context;
	private SessionFactory sessionFactory;
	private ShopService shopService;
	
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
		shopService = (ShopService) context.getBean("shopService");
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		sessionFactory.getCurrentSession().getTransaction().commit();
		context.close();
	}

	@Test
	public void testQueryShopBusinessTimeAndDaysoff() {
		ShopBean bean = new ShopBean();
		bean.setShopID(3);
		ShopBean sbean = shopService.queryShopBusinessTimeAndDaysoff(bean);
		System.out.println("testQueryShopBusinessTimeAndDaysoff="+sbean);
		System.out.println(sbean.getDaysoffBeans());
	}
	
	@Test
	public void testUpdateShopBusinessTime() {
		ShopBean bean = new ShopBean();
		bean.setShopID(11);
		Time stime = Time.valueOf("7:00:00");
		bean.setBeginBusinessTime(stime);
		Time etime = Time.valueOf("13:00:00");
		bean.setEndBusinessTime(etime);
		boolean b = shopService.updateShopBusinessTime(bean);
		System.out.println("testUpdateShopBusinessTime="+b);
	}

	@Test
	public void testUpdateShopBusinessTimeNote() {
		ShopBean bean = new ShopBean();
		bean.setShopID(11);
		bean.setBusinessTimeNote("Hello, Everyone!");
		boolean b = shopService.updateShopBusinessTimeNote(bean);
		System.out.println("testUpdateShopBusinessTimeNote="+b);
	}
	
	@Test
	public void testInsertDaysoff() {
		DaysoffBean bean = new DaysoffBean();
		Date d = java.sql.Date.valueOf("2015-05-04");
		bean.setDaysoff(d);
		bean.setShopID(11);
		boolean b =shopService.insertDaysoff(bean);
		System.out.println("testInsertDaysoff="+b);
	}

	@Test
	public void testDeleteDaysoff() {
		DaysoffBean bean = new DaysoffBean();
		Date d = java.sql.Date.valueOf("2015-05-04");
		bean.setDaysoff(d);
		bean.setShopID(11);
		boolean b =shopService.deleteDaysoff(bean);
		System.out.println("testDeleteDaysoff="+b);
	}
}
