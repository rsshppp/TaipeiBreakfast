package form;

import java.io.Serializable;

public class ShopForm implements Serializable{
	private String shopName;
	private Integer shopID;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getShopID() {
		return shopID;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
}
