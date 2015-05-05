package model.bean;

import java.io.Serializable;
import java.sql.Time;


//店鋪表
public class ShopBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer shopID;
	private String shopName;
	private String shopPhone;
	private String shopCity;
	private String shopArea;
	private String shopAddr;
	private Time lastOrderNoon;
	private Time lastOrderNight;
	private Integer ownID;
	private byte[] logoImage;
	private Boolean shopSuspend;
	private Integer shopCondID;
	private Time beginBusinessTime;
	private Time endBusinessTime;
	private String businessTimeNote;
	
	public ShopBean() {
		
	}

	
	@Override
	public String toString() {
		return "["+shopID+","+shopName+","+shopPhone+","+shopCity+","+shopArea+","+
				shopAddr+","+lastOrderNoon+","+lastOrderNight+","+ownID+","+logoImage
				+","+shopSuspend+","+shopCondID+","+beginBusinessTime+","+endBusinessTime
				+","+businessTimeNote+"]";
	}


	public Integer getShopID() {
		return shopID;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public String getShopCity() {
		return shopCity;
	}

	public void setShopCity(String shopCity) {
		this.shopCity = shopCity;
	}

	public String getShopArea() {
		return shopArea;
	}

	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}

	

	public Time getLastOrderNoon() {
		return lastOrderNoon;
	}


	public void setLastOrderNoon(Time lastOrderNoon) {
		this.lastOrderNoon = lastOrderNoon;
	}


	public Time getLastOrderNight() {
		return lastOrderNight;
	}


	public void setLastOrderNight(Time lastOrderNight) {
		this.lastOrderNight = lastOrderNight;
	}


	public Integer getOwnID() {
		return ownID;
	}

	public void setOwnID(Integer ownID) {
		this.ownID = ownID;
	}

	public byte[] getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(byte[] logoImage) {
		this.logoImage = logoImage;
	}	

	public Boolean getShopSuspend() {
		return shopSuspend;
	}

	public void setShopSuspend(Boolean shopSuspend) {
		this.shopSuspend = shopSuspend;
	}

	public Integer getShopCondID() {
		return shopCondID;
	}

	public void setShopCondID(Integer shopCondID) {
		this.shopCondID = shopCondID;
	}


	public Time getBeginBusinessTime() {
		return beginBusinessTime;
	}


	public void setBeginBusinessTime(Time beginBusinessTime) {
		this.beginBusinessTime = beginBusinessTime;
	}


	public Time getEndBusinessTime() {
		return endBusinessTime;
	}


	public void setEndBusinessTime(Time endBusinessTime) {
		this.endBusinessTime = endBusinessTime;
	}


	public String getBusinessTimeNote() {
		return businessTimeNote;
	}

	public void setBusinessTimeNote(String businessTimeNote) {
		this.businessTimeNote = businessTimeNote;
	}
}
