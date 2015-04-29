package model.bean;

import java.io.Serializable;
import java.sql.Date;

//店鋪表
public class ShopBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer shopID;
	private String shopName;
	private String shopPhone;
	private String shopCity;
	private String shopArea;
	private String shopAddr;
	private Date lastOrderNoon;
	private Date lastOrderNight;
	private Integer ownID;
	private Byte[] logoImage;
	private Boolean shopSuspend;
	private Integer shopStatusID;
	private Date beginBusinessTime;
	private Date endBusinessTime;
	private String businessTimeNote;
	
	public ShopBean() {
		
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

	public Date getLastOrderNoon() {
		return lastOrderNoon;
	}

	public void setLastOrderNoon(Date lastOrderNoon) {
		this.lastOrderNoon = lastOrderNoon;
	}

	public Date getLastOrderNight() {
		return lastOrderNight;
	}

	public void setLastOrderNight(Date lastOrderNight) {
		this.lastOrderNight = lastOrderNight;
	}

	public Integer getOwnID() {
		return ownID;
	}

	public void setOwnID(Integer ownID) {
		this.ownID = ownID;
	}

	public Byte[] getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(Byte[] logoImage) {
		this.logoImage = logoImage;
	}

	

	public Boolean getShopSuspend() {
		return shopSuspend;
	}

	public void setShopSuspend(Boolean shopSuspend) {
		this.shopSuspend = shopSuspend;
	}

	public Integer getShopStatusID() {
		return shopStatusID;
	}

	public void setShopStatusID(Integer shopStatusID) {
		this.shopStatusID = shopStatusID;
	}

	public Date getBeginBusinessTime() {
		return beginBusinessTime;
	}

	public void setBeginBusinessTime(Date beginBusinessTime) {
		this.beginBusinessTime = beginBusinessTime;
	}

	public Date getEndBusinessTime() {
		return endBusinessTime;
	}

	public void setEndBusinessTime(Date endBusinessTime) {
		this.endBusinessTime = endBusinessTime;
	}

	public String getBusinessTimeNote() {
		return businessTimeNote;
	}

	public void setBusinessTimeNote(String businessTimeNote) {
		this.businessTimeNote = businessTimeNote;
	}
}
