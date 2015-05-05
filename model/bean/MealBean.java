package model.bean;

import java.io.Serializable;

//餐點表-Gary
public class MealBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer mealID;
	private String mealName;
	private Double price;
	private Integer shopID;
	private byte[] mealImage;
	private Boolean mealStatus;
	private MealKindListBean mealKindListBean;
	
	
	@Override
	public String toString() {
		
		return "mealID="+mealID+"，mealName="+mealName+"，price="+price+"，shopID="+shopID+"，mealImage="+mealImage+"，mealStatus="+mealStatus+mealKindListBean;
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
	public Integer getShopID() {
		return shopID;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	public byte[] getMealImage() {
		return mealImage;
	}
	public void setMealImage(byte[] mealImage) {
		this.mealImage = mealImage;
	}
	public Boolean getMealStatus() {
		return mealStatus;
	}
	public void setMealStatus(Boolean mealStatus) {
		this.mealStatus = mealStatus;
	}
	public MealKindListBean getMealKindListBean() {
		return mealKindListBean;
	}
	public void setMealKindListBean(MealKindListBean mealKindListBean) {
		this.mealKindListBean = mealKindListBean;
	}
	
}
