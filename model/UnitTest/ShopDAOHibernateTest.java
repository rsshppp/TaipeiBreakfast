package UnitTest;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;

import model.bean.DaysoffBean;
import model.bean.MealBean;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.ShopDAO;
import model.dao.imp.ShopDAOHibernate;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShopDAOHibernateTest {
	private ConfigurableApplicationContext context;
	private SessionFactory sessionFactory;
	private ShopDAO dao;
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
		sessionFactory.getCurrentSession().beginTransaction();
		dao = (ShopDAOHibernate) context.getBean("shopDAO");
	}

	@After
	public void tearDown() throws Exception {
		sessionFactory.getCurrentSession().getTransaction().commit();
		context.close();
	}

//	@Test
	public void testUpdateBusinessTime() {
		
		//假設傳入營業時間與ShopID而已,
		ShopBean bean = new ShopBean();
		Time bTime = Time.valueOf("9:00:00");
		bean.setBeginBusinessTime(bTime);
		Time eTime = Time.valueOf("12:30:00");
		bean.setEndBusinessTime(eTime);
		bean.setShopID(3);
		System.out.println(bTime);
		System.out.println(eTime);
		//使用方法 UpdateBusinessTime
		boolean b = dao.updateBusinessTime(bean);
		System.out.println("updateresult="+b);
		
		
	}
	
	@Test
	public void testUpdateBusinessTimeNote(){
		//假設傳入營業筆記與ShopID而已,
		ShopBean bean = new ShopBean();
		bean.setShopID(3);
		bean.setBusinessTimeNote("tests");
		boolean b = dao.updateBusinessTimeNote(bean);
		System.out.println("testUpdateBusinessTimeNote="+b);
	}
	//測試查詢店鋪營業時間，查詢營業註解、查詢休假日
	@Test
	public void testSelect(){
		ShopBean bean =dao.select(3);
		System.out.println(bean.getBusinessTimeNote());
		System.out.println(bean.getBeginBusinessTime());
		System.out.println(bean.getEndBusinessTime());
		Iterator iterator = bean.getDaysoffBeans().iterator();
		while(iterator.hasNext()){
			DaysoffBean dbean = (DaysoffBean) iterator.next();
		System.out.println(dbean.getDaysoff());
		}
	}
	@Test
	public void getShops(){
		OwnerBean obean = new OwnerBean();
		obean.setOwnID(1);
		List<ShopBean> beans = dao.queryShops(obean);
		Iterator iterator=beans.iterator();
		while(iterator.hasNext()){
			ShopBean bean = (ShopBean) iterator.next();
			System.out.println("ShopBean="+bean);
			Iterator mbeans = bean.getMealBeans().iterator();
			while(mbeans.hasNext()){
				MealBean mbean = (MealBean) mbeans.next();
				System.out.println("mbean="+mbean);
			}
		}
	}
}
