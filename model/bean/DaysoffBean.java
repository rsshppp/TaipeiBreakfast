package model.bean;

import java.io.Serializable;
import java.sql.Date;

//店鋪休假日表
public class DaysoffBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer daysoffID;
	private Integer shopID;
	private Date  daysoff;	
	
	public DaysoffBean() {
		
	}
	
	@Override
	public String toString() {
		return "daysoffID:"+daysoffID+", shopID:"+shopID+", daysoff:"+daysoff;
	}

	public Integer getDaysoffID() {
		return daysoffID;
	}

	public void setDaysoffID(Integer daysoffID) {
		this.daysoffID = daysoffID;
	}

	public Integer getShopID() {
		return shopID;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}

	public Date getDaysoff() {
		return daysoff;
	}

	public void setDaysoff(Date daysoff) {
		this.daysoff = daysoff;
	}
	
}
