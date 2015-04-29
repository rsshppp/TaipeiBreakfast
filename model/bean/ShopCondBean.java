package model.bean;

import java.io.Serializable;

//店鋪狀態表
public class ShopCondBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer shopCondID;
	private String shopCond;
	
	public ShopCondBean() {
		
	}

	public Integer getShopCondID() {
		return shopCondID;
	}

	public void setShopCondID(Integer shopCondID) {
		this.shopCondID = shopCondID;
	}

	public String getShopCond() {
		return shopCond;
	}

	public void setShopCond(String shopCond) {
		this.shopCond = shopCond;
	}
}
