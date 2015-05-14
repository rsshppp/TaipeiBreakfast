package UnitTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.bean.MealBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.dao.MealDAO;
import model.dao.OrderSumDAO;
import model.dao.imp.OrderSumDAOHibernate;
import model.dao.imp.MealDAOHibernate;

import org.apache.tomcat.jni.Time;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderSumDAOHibernateUnitTest {
	private ConfigurableApplicationContext context;
	private SessionFactory sessionFactory;
	private OrderSumDAO dao;
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
		dao = (OrderSumDAOHibernate) context.getBean("orderSumDAO");
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
		// 查詢該店鋪所有訂單
		// 假設前面傳來店鋪資訊，店鋪ID=3
		ShopBean bean = new ShopBean();
		bean.setShopID(3);
		List<OrderSumBean> orderSums = dao.queryOrderSum(bean);
		Iterator list = orderSums.iterator();
		while (list.hasNext()) {
			OrderSumBean orderSum = (OrderSumBean) list.next();
			System.out.println("OrderSum=" + orderSum);
			// 取得訂單明細
			Iterator orderDetails = orderSum.getOrderDetail().iterator();
			while (orderDetails.hasNext()) {
				OrderDetailBean orderDetail = (OrderDetailBean) orderDetails
						.next();
				System.out.println("OrderDetail.getPrice=" + orderDetail);
				// 取得餐點名字
				MealBean meal = orderDetail.getMealBean();
				System.out.println("meal.getMealName=" + meal);
			}
		}

	}

	@Test
	public void testUpdateOrderCond() {

		// 更改訂單狀態
		// 假設前面傳來更改某筆訂單狀態和狀態更改值
		OrderSumBean update = new OrderSumBean();
		update.setOrderSumID(2);
		update.setOrderCondID(2);
		// 執行更改訂單狀態方法
		boolean b = dao.updateOrderCond(update);
		System.out.println("testUpdateOrderCondOrderSumBean=" + b);
	}

	@Test
	public void testInsertOrder() {
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

		boolean b = dao.insertOrder(obean);
		System.out.println("testInsertOrder=" + b);

	}

	@Test
	public void queryOneOrderSum() {
		Integer OrderSumID = 20;
		OrderSumBean obean = dao.queryOneOrderSum(OrderSumID);
		System.out.println("queryOneOrderSum" + obean);
	}

	@Test
	public void queryOneOrderSumPage() {
		// 查詢該店鋪所有訂單
		// 假設前面傳來店鋪資訊，店鋪ID=3
		ShopBean bean = new ShopBean();
		bean.setShopID(3);
		List<OrderSumBean> orderSums = dao.queryOrderSum(bean, 0, 1);
		Iterator list = orderSums.iterator();
		while (list.hasNext()) {
			OrderSumBean orderSum = (OrderSumBean) list.next();
			System.out.println("queryOneOrderSumPage=" + orderSum);			
		}
	}
}
