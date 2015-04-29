package model.bean;

import java.io.Serializable;
import java.sql.Date;

public class SpecialPriceBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer mealID;
	private Integer specialPrice;
	private Date startDate;
	private Date endDate;
	
	public SpecialPriceBean() {

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
}
