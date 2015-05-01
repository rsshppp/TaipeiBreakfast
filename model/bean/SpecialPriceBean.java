package model.bean;

import java.io.Serializable;
import java.sql.Date;

//優惠價格表
public class SpecialPriceBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer SpecialPriceID;
	private Integer mealID;
	private Integer specialPrice;
	//specialPrice 要改成用 Double
	private Date startDate;
	private Date endDate;
	

	public Integer getSpecialPriceID() {
		return SpecialPriceID;
	}
	public void setSpecialPriceID(Integer specialPriceID) {
		SpecialPriceID = specialPriceID;
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
