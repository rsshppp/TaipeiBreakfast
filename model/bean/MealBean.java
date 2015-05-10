package model.bean;

import java.io.Serializable;
import java.util.Set;

//餐點表-Gary
public class MealBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer mealID;
	private String mealName;
	private Double price;
	private Integer shopID;
	private byte[] mealImage;
	private Boolean mealStatus;
	private Integer mealKindID;
	private MealKindListBean mealKindListBean;
	private ShopBean shopBean; // 關聯 -chunting
	private Set<SpecialPriceBean> specialPriceBean; // 關聯 -chunting
	@Override
	public String toString() {

		return "{ mealID:" + mealID + " , mealName:" + mealName + " , price:"
				+ price + " , shopID:" + shopID + " , mealImage:" + mealImage
				+ " , mealStatus:" + mealStatus + " , mealKindID:" + mealKindID
				+ " , mealKindListBean:" + mealKindListBean + " }";
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

	public Integer getMealKindID() {
		return mealKindID;
	}

	public void setMealKindID(Integer mealKindID) {
		this.mealKindID = mealKindID;
	}

	public MealKindListBean getMealKindListBean() {
		return mealKindListBean;
	}

	public void setMealKindListBean(MealKindListBean mealKindListBean) {
		this.mealKindListBean = mealKindListBean;
	}

	public ShopBean getShopBean() {
		return shopBean;
	}

	public void setShopBean(ShopBean shopBean) {
		this.shopBean = shopBean;
	}

	public Set<SpecialPriceBean> getSpecialPriceBean() {
		return specialPriceBean;
	}

	public void setSpecialPriceBean(Set<SpecialPriceBean> specialPriceBean) {
		this.specialPriceBean = specialPriceBean;
	}
	
}
