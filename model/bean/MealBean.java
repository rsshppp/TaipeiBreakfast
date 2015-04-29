package model.bean;

import java.io.Serializable;

//餐點表-Gary
public class MealBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer mealID;
	private String mealName;
	private Double price;
	private Integer shopID;
	private Byte mealImage;
	private Boolean mealStatus;
	private Integer mealKindID;
	
	public Integer getMealID() {
		return mealID;
	}
	public void setMealID(Integer mealID) {
		this.mealID = mealID;
	}
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getShopID() {
		return shopID;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	public Byte getMealImage() {
		return mealImage;
	}
	public void setMealImage(Byte mealImage) {
		this.mealImage = mealImage;
	}
	public Boolean getMealStatus() {
		return mealStatus;
	}
	public void setMealStatus(Boolean mealStatus) {
		this.mealStatus = mealStatus;
	}
	public Integer getMealKindID() {
		return mealKindID;
	}
	public void setMealKindID(Integer mealKindID) {
		this.mealKindID = mealKindID;
	}
}
