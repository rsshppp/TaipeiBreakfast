package UnitTest.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import model.bean.MealBean;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.bean.SpecialPriceBean;
import model.service.SpecialPriceService;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpecialPriceServiceTest {
	private ConfigurableApplicationContext context;
	private SpecialPriceService specialPriceService;
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		specialPriceService = (SpecialPriceService) context.getBean("specialPriceService");
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		sessionFactory.getCurrentSession().getTransaction().commit();
		context.close();
	}

	@Test
	public void testQueryShops() {
		OwnerBean bean = new OwnerBean();
		bean.setOwnID(1);
		List<ShopBean> list = specialPriceService.queryShops(bean);
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			ShopBean sbean = (ShopBean) iterator.next();
			System.out.println("testQueryShops, shopbean="+sbean);
			System.out.println("testQueryShops, meal="+sbean.getMealBeans());
		}
	}

	@Test
	public void testQuerySpecialPrice() {
		OwnerBean bean = new OwnerBean();
		bean.setOwnID(1);
		List<SpecialPriceBean> list = specialPriceService.querySpecialPrice(bean);
		System.out.println("testQuerySpecialPrice="+list);
	}

	@Test
	public void testInsertSpecialPrice() {
		SpecialPriceBean bean = new SpecialPriceBean();
		MealBean mbean = new MealBean();
		mbean.setMealID(4);
		Date sDate = java.sql.Date.valueOf("2015-06-30");
		Date eDate = java.sql.Date.valueOf("2015-07-01");
		bean.setEndDate(eDate);
		bean.setMealBean(mbean);
		bean.setStartDate(sDate);
		bean.setSpecialPrice(823);
		boolean b = specialPriceService.insertSpecialPrice(bean);
		System.out.println("testInsertSpecialPrice="+b);
	}

	@Test
	public void testUpdateSpecialPrice() {
		SpecialPriceBean bean = new SpecialPriceBean();
		MealBean mbean = new MealBean();
		mbean.setMealID(4);
		Date sDate = java.sql.Date.valueOf("2015-07-30");
		Date eDate = java.sql.Date.valueOf("2015-08-01");
		bean.setEndDate(eDate);
		bean.setMealBean(mbean);
		bean.setStartDate(sDate);
		bean.setSpecialPrice(8);
		bean.setSpecialPriceID(6);
		boolean b = specialPriceService.updateSpecialPrice(bean);
		System.out.println("testUpdateSpecialPrice="+b);
	}

	@Test
	public void testDeleteSpecialPrice() {
		SpecialPriceBean bean = new SpecialPriceBean();
		bean.setSpecialPriceID(6);
		boolean b = specialPriceService.deleteSpecialPrice(bean);
		System.out.println("testDeleteSpecialPrice="+b);
	}

}
