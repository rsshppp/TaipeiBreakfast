package UnitTest;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import model.bean.MealBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
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

public class OrderSumDAOHibernateUnitTest {
	private ConfigurableApplicationContext context;
	private SessionFactory sessionFactory;
	private OrderSumDAO dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				"beans.cfg.xml");
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		dao = (OrderSumDAOHibernate) context
				.getBean("OrderSumDAO");
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		sessionFactory.getCurrentSession().getTransaction().commit();
		context.close();
		System.out.println("done");
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

//	@Test
	public void testUpdateOrderCondOrderSumBean() {
		
		// 更改訂單狀態
		// 假設前面傳來更改某筆訂單狀態和狀態更改值
		OrderSumBean update = new OrderSumBean();
		update.setOrderSumID(2);
		update.setOrderCondID(2);
		// 執行更改訂單狀態方法
		boolean b = dao.updateOrderCond(update);
		System.out.println(b);
	}

}
