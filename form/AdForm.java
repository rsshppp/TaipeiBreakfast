package form;

import java.io.File;
import java.io.Serializable;

public class AdForm implements Serializable{
	private Integer shopID;
	private String shopName;
	private Integer days;
	private String title;
	private String context;
	private File adImage;
	private String adStatus;
	private Integer adStatusID;
	
	
	@Override
	public String toString() {
		
		return "{shopID:"+shopID+",days:"+days+",title:"+title+",context:"+context+"}";
	}
	
	public Integer getAdStatusID() {
		return adStatusID;
	}

	public void setAdStatusID(Integer adStatusID) {
		this.adStatusID = adStatusID;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(String adStatus) {
		this.adStatus = adStatus;
	}

	public Integer getShopID() {
		return shopID;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public File getAdImage() {
		return adImage;
	}
	public void setAdImage(File adImage) {
		this.adImage = adImage;
	}
	
	
}
