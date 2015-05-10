package UnitTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import model.bean.MealBean;
import model.bean.OwnerBean;
import model.bean.SpecialPriceBean;
import model.dao.ShopDAO;
import model.dao.SpecialPriceDAO;
import model.dao.imp.ShopDAOHibernate;
import model.dao.imp.SpecialPriceDAOHibernate;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpecialPriceDAOHibernateTest {
	private ConfigurableApplicationContext context;
	private SessionFactory sessionFactory;
	private SpecialPriceDAO dao;
	
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		dao = (SpecialPriceDAOHibernate) context.getBean("specialPriceDAO");
	}

	@After
	public void tearDown() throws Exception {
		sessionFactory.getCurrentSession().getTransaction().commit();
		context.close();
	}
	
//	@Test
	public void testQuerySpecialPriceOwnerBean() {
		OwnerBean bean = new OwnerBean();
		bean.setOwnID(1);
		List<SpecialPriceBean> list = dao.querySpecialPrice(bean);
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			SpecialPriceBean sbean = (SpecialPriceBean) iterator.next();
			System.out.println("SpecialPriceBean="+sbean);
		}
	}
	
	@Test
	public void testInsertSpecialPrice(){
		SpecialPriceBean bean = new SpecialPriceBean();
		MealBean mbean = new MealBean();
		mbean.setMealID(3);
		Date sDate = java.sql.Date.valueOf("2015-06-30");
		Date eDate = java.sql.Date.valueOf("2015-07-01");
		bean.setEndDate(eDate);
		bean.setMealBean(mbean);
		bean.setStartDate(sDate);
		bean.setSpecialPrice(916);
		boolean b= dao.insertSpecialPrice(bean);
		System.out.println("testInsertSpecialPrice="+b);
	}
	
	@Test
	public void testUpdateSpecialPrice(){
		SpecialPriceBean bean = new SpecialPriceBean();
		bean.setSpecialPrice(119);
		Date sDate = java.sql.Date.valueOf("2015-05-22");
		Date eDate = java.sql.Date.valueOf("2015-06-27");
		bean.setStartDate(sDate);
		bean.setEndDate(eDate);
		MealBean mbean = new MealBean();
		mbean.setMealID(8);
		bean.setMealBean(mbean);
		bean.setSpecialPriceID(18);
		boolean b = dao.updateSpecialPrice(bean);
		System.out.println("testUpdateSpecialPrice="+b);
	}
	@Test
	public void testDeleteSpecialPrice(){
		SpecialPriceBean bean = new SpecialPriceBean();
		bean.setSpecialPriceID(18);
		boolean b =dao.deleteSpecialPrice(bean);
		System.out.println("testDeleteSpecialPrice="+b);
	}
}
