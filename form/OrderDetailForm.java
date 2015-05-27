package form;

import java.io.Serializable;

//訂單明細表
public class OrderDetailForm implements Serializable{

	private Integer orderDetailID;
	private Integer mealId;
	private Integer count;
	private Double price;
	private Integer orderSumID;
	private String mealName;
	
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
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

}
