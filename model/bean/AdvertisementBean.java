package model.bean;

//餐點狀態表-Gary
public class AdvertisementBean {
	private Integer advertisementID;
	private String context;
	private Byte image;
	private Integer days;
	private Boolean passed;
	private Integer advertisementStatusID;
	private Integer shopID;
	
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
	public Byte getImage() {
		return image;
	}
	public void setImage(Byte image) {
		this.image = image;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Boolean getPassed() {
		return passed;
	}
	public void setPassed(Boolean passed) {
		this.passed = passed;
	}
	public Integer getAdvertisementStatusID() {
		return advertisementStatusID;
	}
	public void setAdvertisementStatusID(Integer advertisementStatusID) {
		this.advertisementStatusID = advertisementStatusID;
	}
	public Integer getShopID() {
		return shopID;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
}
