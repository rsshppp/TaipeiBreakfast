package model.bean;

import java.io.Serializable;
import java.sql.Date;

//店鋪休假日表
public class DaysOffBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer daysOffID;
	private Integer shopID;
	private Date daysOff;
	
	public DaysOffBean() {
		
	}

	public Integer getDaysOffID() {
		return daysOffID;
	}

	public void setDaysOffID(Integer daysOffID) {
		this.daysOffID = daysOffID;
	}

	public Integer getShopID() {
		return shopID;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}

	public Date getDaysOff() {
		return daysOff;
	}

	public void setDaysOff(Date daysOff) {
		this.daysOff = daysOff;
	}
}
