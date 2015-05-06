package model.bean;

import java.io.Serializable;

//餐點狀態表-Gary
public class AdvertisementBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer advertisementID;
	private String context;
	private byte[] image;
	private Integer days;
	private Integer advertisementStatusID;
	private AdvertisementStatusBean advertisementStatusBean; 
	private Integer shopID;
	private ShopBean shopBean;
	
	
	@Override
	public String toString() {
		
		return "advertisementID="+advertisementID+"，context="+context+"，image="+image+"，days="+days
				+"，shopID="+shopID+"，"+advertisementStatusBean;
	}
	public Integer getAdvertisementID() {
		return advertisementID;
	}
	public void setAdvertisementID(Integer advertisementID) {
		this.advertisementID = advertisementID;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getAdvertisementStatusID() {
		return advertisementStatusID;
	}
	public void setAdvertisementStatusID(Integer advertisementStatusID) {
		this.advertisementStatusID = advertisementStatusID;
	}
	public AdvertisementStatusBean getAdvertisementStatusBean() {
		return advertisementStatusBean;
	}
	public void setAdvertisementStatusBean(
			AdvertisementStatusBean advertisementStatusBean) {
		this.advertisementStatusBean = advertisementStatusBean;
	}
	public Integer getShopID() {
		return shopID;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	public ShopBean getShopBean() {
		return shopBean;
	}
	public void setShopBean(ShopBean shopBean) {
		this.shopBean = shopBean;
	}
	
}
