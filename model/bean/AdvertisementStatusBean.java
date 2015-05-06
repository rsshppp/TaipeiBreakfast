package model.bean;

import java.io.Serializable;

//廣告狀態表-Gary
public class AdvertisementStatusBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer advertisementStatusID;
	private String advertisementStatus;
	
	
	@Override
	public String toString() {
		return "advertisementStatusID="+advertisementStatusID+"，advertisementStatus="+advertisementStatus;
	}
	public Integer getAdvertisementStatusID() {
		return advertisementStatusID;
	}
	public void setAdvertisementStatusID(Integer advertisementStatusID) {
		this.advertisementStatusID = advertisementStatusID;
	}
	public String getAdvertisementStatus() {
		return advertisementStatus;
	}
	public void setAdvertisementStatus(String advertisementStatus) {
		this.advertisementStatus = advertisementStatus;
	}
}
