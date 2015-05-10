package model.bean;

import java.io.Serializable;
import java.sql.Date;

//優惠價格表
public class SpecialPriceBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer specialPriceID;
	private Integer mealID;
	private Integer specialPrice;
	//specialPrice 要改成用 Double
	private Date startDate;
	private Date endDate;
	private MealBean mealBean;
	
	
	
	public Integer getSpecialPriceID() {
		return specialPriceID;
	}
	@Override
	public String toString() {
		return "SpecialPriceID:"+specialPriceID+"mealID:"+mealID+"specialPrice:"+specialPrice+
				"startDate:"+startDate+"endDate:"+endDate;
	}
	public void setSpecialPriceID(Integer specialPriceID) {
		this.specialPriceID = specialPriceID;
	}
	public Integer getMealID() {
		return mealID;
	}
	public void setMealID(Integer mealID) {
		this.mealID = mealID;
	}
	public Integer getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(Integer specialPrice) {
		this.specialPrice = specialPrice;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public MealBean getMealBean() {
		return mealBean;
	}
	public void setMealBean(MealBean mealBean) {
		this.mealBean = mealBean;
	}
}
