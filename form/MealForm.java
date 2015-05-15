package form;

import java.io.File;
import java.io.Serializable;
import java.util.Set;

//餐點表-Gary
public class MealForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer mealID;
	private String mealName;
	private Double price;
	private Integer shopID;
	private File mealImage;
	private Boolean mealStatus;
	private Integer mealKindID;
	
	@Override
	public String toString() {

		return "{ mealID:" + mealID + " , mealName:" + mealName + " , price:"
				+ price + " , shopID:" + shopID + " , mealImage:" + mealImage
				+ " , mealStatus:" + mealStatus + " , mealKindID:" + mealKindID
				 + " }";
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

	public File getMealImage() {
		return mealImage;
	}

	public void setMealImage(File mealImage) {
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
