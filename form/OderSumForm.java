package form;

import java.sql.Timestamp;

public class OderSumForm {
	
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
	
}
