package model.Bean;

public class OrderDetailBean {
	private int OrderDetailID;
	private int MealID;
	private int Count;
	private float Price;
		//下訂單時的商品價格,避免因物價變動而出錯
	private int OrderSumID;
	
	public int getOrderDetailID() {
		return OrderDetailID;
	}
	public void setOrderDetailID(int orderDetailID) {
		OrderDetailID = orderDetailID;
	}
	public int getMealID() {
		return MealID;
	}
	public void setMealID(int mealID) {
		MealID = mealID;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public int getOrderSumID() {
		return OrderSumID;
	}
	public void setOrderSumID(int orderSumID) {
		OrderSumID = orderSumID;
	}
	
}
