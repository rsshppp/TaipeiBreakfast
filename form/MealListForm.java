package form;

import java.io.Serializable;

public class MealListForm implements Serializable{
	private Integer mealID;
	private String mealName;
	private Double price;
	private byte[] mealImage;
	private String mealStatus;
	private String mealKindName;
	private Integer mealKindID;
	private String shopName;
	private Integer shopID;
	
	public Integer getShopID() {
		return shopID;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
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
	public byte[] getMealImage() {
		return mealImage;
	}
	public void setMealImage(byte[] mealImage) {
		this.mealImage = mealImage;
	}
	public String getMealStatus() {
		return mealStatus;
	}
	public void setMealStatus(String mealStatus) {
		this.mealStatus = mealStatus;
	}
	public String getMealKindName() {
		return mealKindName;
	}
	public void setMealKindName(String mealKindName) {
		this.mealKindName = mealKindName;
	}
	public Integer getMealKindID() {
		return mealKindID;
	}
	public void setMealKindID(Integer mealKindID) {
		this.mealKindID = mealKindID;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	
}
