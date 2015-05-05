package model.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.dao.imp.OrderSumDAOHibernate;
import model.misc.HibernateUtil;

import org.hibernate.SessionFactory;


//總訂單表
public class OrderSumBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// Table屬性
	private Integer orderSumID;
	private Integer shopID;
	private Integer memberID;
	private Double totalPrice;
	private Timestamp orderTime;	//原始java.sql.Date 改成 java.sql.Timestamp
	private Timestamp expectTime;	//原始java.sql.Date 改成 java.sql.Timestamp
	private String memo;
	private Integer starsForOwn;
	private String evaluateForShop;
	private Integer orderCondID;
	// Table對應其他欄位
	private Set<OrderDetailBean> OrderDetail;

	public OrderSumBean() {
		OrderDetail = new HashSet<OrderDetailBean>();
	}

	public Set<OrderDetailBean> getOrderDetail() {
		return OrderDetail;
	}

	public void setOrderDetail(Set<OrderDetailBean> orderDetail) {
		OrderDetail = orderDetail;
	}

	public Integer getOrderSumID() {
		return orderSumID;
	}

	public void setOrderSumID(Integer orderSumID) {
		this.orderSumID = orderSumID;
	}

	public Integer getShopID() {
		return shopID;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Timestamp getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(Timestamp expectTime) {
		this.expectTime = expectTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getStarsForOwn() {
		return starsForOwn;
	}

	public void setStarsForOwn(Integer starsForOwn) {
		this.starsForOwn = starsForOwn;
	}

	public String getEvaluateForShop() {
		return evaluateForShop;
	}

	public void setEvaluateForShop(String evaluateForShop) {
		this.evaluateForShop = evaluateForShop;
	}

	public Integer getOrderCondID() {
		return orderCondID;
	}

	public void setOrderCondID(Integer orderCondID) {
		this.orderCondID = orderCondID;
	}

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		OrderSumDAOHibernate dao = new OrderSumDAOHibernate();
		dao.setSessionFactory(sessionFactory);
		ShopBean bean = new ShopBean();
		bean.setShopID(3);
		List<OrderSumBean> orderSums = dao.queryOrderSum(bean);
		Iterator list = orderSums.iterator();
		while(list.hasNext()){
			OrderSumBean orderSum = (OrderSumBean) list.next();
			System.out.println("orderSumDetail.TotalPrice="+orderSum.getTotalPrice());
			Iterator orderDetails = orderSum.getOrderDetail().iterator();
			while(orderDetails.hasNext()){
				OrderDetailBean orderDetail = (OrderDetailBean) orderDetails.next();
				System.out.println("orderDetail.getPrice="+orderDetail.getPrice());
				MealBean meal = orderDetail.getMeal();
				System.out.println("meal.getMealName="+meal.getMealName());
			}
		}
		sessionFactory.close();
	}
	
}
