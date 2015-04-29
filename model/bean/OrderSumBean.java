package model.bean;

import java.io.Serializable;
import java.sql.Date;

//總訂單表
public class OrderSumBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer orderSumID;
	private Integer shopID;
	private Integer memberID;
	private Double totalPrice;
	private Date orderTime;
	private Date expectTime;
	private String memo;
	private Integer starsForOwn;
	private String evaluateForShop;
	private Integer orderCondID;
	
	public OrderSumBean() {
		
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

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(Date expectTime) {
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

	
}
