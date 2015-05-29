package form;

import java.io.File;
import java.io.Serializable;
import java.sql.Time;



//店鋪表
public class ShopFormByWeng implements Serializable{
	
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
	private File logoImage;


	private Time beginBusinessTime;
	private Time endBusinessTime;
	private String businessTimeNote;
	
	

	public ShopFormByWeng() {
		
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



	public File getLogoImage() {
		return logoImage;
	}



	public void setLogoImage(File logoImage) {
		this.logoImage = logoImage;
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



	@Override
	public String toString() {
		return "{ shopName:"+shopName+", shopPhone:"+shopPhone+", shopCity:"
				+shopCity+", shopArea:"+shopArea+", shopAddr:"+shopAddr+", lastOrderNoon:"+lastOrderNoon
				+", lastOrderNight:"+lastOrderNight+", ownID:"+ownID+", logoImage:"+logoImage
				+", beginBusinessTime:"+beginBusinessTime+
				", endBusinessTime:"+endBusinessTime+", businessTimeNote:"+businessTimeNote
				+" }";
		
	}


	

	
}
