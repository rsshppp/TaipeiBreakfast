package model.bean;

import java.io.Serializable;

//訂單明細表
public class OrderDetailBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer orderDetailID;
	private Integer mealId;
	private Integer count;
	private Double price;	//下訂單時的商品價格,避免因物價變動而出錯
	private Integer orderSumID;
	
	private MealBean mealBean;	//關聯 -廷

	public Integer getOrderDetailID() {
		return orderDetailID;
	}
	public void setOrderDetailID(Integer orderDetailID) {
		this.orderDetailID = orderDetailID;
	}
	public Integer getMealId() {
		return mealId;
	}
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getOrderSumID() {
		return orderSumID;
	}
	public void setOrderSumID(Integer orderSumID) {
		this.orderSumID = orderSumID;
	}
	public MealBean getMealBean() {
		return mealBean;
	}
	public void setMealBean(MealBean mealBean) {
		this.mealBean = mealBean;
	}
	
}
