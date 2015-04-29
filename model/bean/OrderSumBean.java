package model.bean;

import java.io.Serializable;
import java.sql.Date;

public class OrderSumBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer orderSumId;
	private Integer shopId;
	private Integer memberId;
	private Float totalPrice;
	private Date orderTime;
	private Date expectTime;
	private String memo;
	private Integer starsForOwn;
	private String evaluateForShop;
	private Integer orderCondId;
	
	public OrderSumBean() {
		
	}
	
	public Integer getOrderSumId() {
		return orderSumId;
	}
	public void setOrderSumId(Integer orderSumId) {
		this.orderSumId = orderSumId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
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
	public Integer getOrderCondId() {
		return orderCondId;
	}
	public void setOrderCondId(Integer orderCondId) {
		this.orderCondId = orderCondId;
	}
}
