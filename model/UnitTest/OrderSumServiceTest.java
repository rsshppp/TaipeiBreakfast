package UnitTest.service;

import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import model.bean.MealBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.dao.MealDAO;
import model.dao.imp.MealDAOHibernate;
import model.service.OrderSumService;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderSumServiceTest {
	private ConfigurableApplicationContext context;
	private SessionFactory sessionFactory;
	private OrderSumService orderSumService;
	private MealDAO mealDAO;

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
		orderSumService = (OrderSumService) context.getBean("orderSumService");
		mealDAO = (MealDAOHibernate) context.getBean("mealDAO");
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		sessionFactory.getCurrentSession().getTransaction().commit();
		context.close();
	}

	@Test
	public void testQueryOrderSum() {
		ShopBean sbean = new ShopBean();
		sbean.setShopID(11);
		List<OrderSumBean> result = orderSumService.queryOrderSum(sbean);
		Iterator iterator = result.iterator();
		while (iterator.hasNext()) {
			OrderSumBean bean = (OrderSumBean) iterator.next();
			System.out.println("testQueryOrderSum" + bean);
		}
	}

	@Test
	public void testUpdateOrderCond() {
		OrderSumBean obean = new OrderSumBean();
		obean.setOrderSumID(22);
		obean.setOrderCondID(1);
		boolean b = orderSumService.updateOrderCond(obean);
		System.out.println("testUpdateOrderCond=" + b);
	}

	@Test
	public void insertOrder() {
		// 假設已經組裝好OrderSumBean物件
		MealBean mbean1 = mealDAO.selectOneMeal(5);
		MealBean mbean2 = mealDAO.selectOneMeal(6);
		System.out.println("mbaen1=" + mbean1);
		System.out.println("mbaen2=" + mbean2);

		// 產生OrderSum並設定參數
		OrderSumBean obean = new OrderSumBean();
		obean.setShopID(11);
		obean.setMemberID(1);
		obean.setTotalPrice(70.0);
		obean.setExpectTime(new Timestamp(new java.util.Date().getTime()));
		obean.setMemo("Hi");
		obean.setOrderCondID(1);

		// 建立訂單明細1
		OrderDetailBean dbean1 = new OrderDetailBean();
		dbean1.setCount(1);
		dbean1.setPrice(mbean1.getPrice());
		dbean1.setMealBean(mbean1); // 設定與Meal關係
		dbean1.setOrderSumBean(obean); // 設定與OrderSum關係
		// 建立訂單明細2
		OrderDetailBean dbean2 = new OrderDetailBean();
		dbean2.setCount(1);
		dbean2.setPrice(mbean2.getPrice());
		dbean2.setMealBean(mbean2); // 設定與Meal關係
		dbean2.setOrderSumBean(obean); // 設定與OrderSum關係

		// OrderSum與OrderDetail關係
		obean.addOrderDetail(dbean1);
		obean.addOrderDetail(dbean2);

		boolean b = orderSumService.insertOrder(obean);
		System.out.println("insertOreder="+b);
	}
}
