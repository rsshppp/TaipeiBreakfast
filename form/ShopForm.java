package form;

import java.io.Serializable;
import java.sql.Time;

public class ShopForm implements Serializable{
	private String shopName;
	private Integer shopID;
	private String shopName;
	private String shopPhone;
	private String shopCity;
	private String shopArea;
	private String shopAddr;
	private Integer ownID;
	private byte[] logoImage;
	private Boolean shopSuspend;
	private Integer shopCondID;
	private Time beginBusinessTime;
	private String businessTimeNote;

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
	public String getBusinessTimeNote() {
		return businessTimeNote;
	}
	public void setBusinessTimeNote(String businessTimeNote) {
		this.businessTimeNote = businessTimeNote;
	}
	
}
