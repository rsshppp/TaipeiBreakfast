package model.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.dao.OrderSumDAO;
import model.dao.imp.OrderSumDAOHibernate;
import model.misc.HibernateUtil;

import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


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
	
	private Set<OrderDetailBean> orderDetailBean;// Table對應其他表格
    private ShopBean shopBean;                   //關聯shop - 宗鈺
    private MemberBean memberBean;               //關聯Member - 宗鈺
    
	@Override
	public String toString() {
		return "{\"orderSumID\":" + orderSumID + ", \"shopID\":" + shopID
				+ ", \"memberID\":" + memberID + ", \"totalPrice\":"+ totalPrice 
				+ ", \"orderTime\":" + orderTime+ ", \"expectTime\":" + expectTime 
				+ ", \"memo\":" + memo+ ", \"starsForOwn\":" + starsForOwn 
				+ ", \"evaluateForShop\":"+ evaluateForShop + ", \"orderCondID\":" + orderCondID
				+ "\"orderDetailBean\":" + orderDetailBean + ", shopBean:"+ shopBean + ", memberBean:" + memberBean;
	}

	public OrderSumBean() {
		orderDetailBean = new HashSet<OrderDetailBean>();
	}

	public Set<OrderDetailBean> getOrderDetail() {
		return orderDetailBean;
	}

	public void setOrderDetail(Set<OrderDetailBean> orderDetailBean) {
		this.orderDetailBean = orderDetailBean;
	}

	//存入OrderDetailBean 到Set<OrderDetailBean>裡
	public void addOrderDetail(OrderDetailBean bean){
		orderDetailBean.add(bean);
	}
	
	public void removeOrderDetail(OrderDetailBean bean){
		orderDetailBean.remove(bean);
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

	public ShopBean getShopBean() {
		return shopBean;
	}

	public void setShopBean(ShopBean shopBean) {
		this.shopBean = shopBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	
}
