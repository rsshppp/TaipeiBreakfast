package model.bean;

import java.io.Serializable;

public class OrderCondBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer orderCondID;
	private String orderCond;
	
	public OrderCondBean() {
		
	}

	public Integer getOrderCondID() {
		return orderCondID;
	}

	public void setOrderCondID(Integer orderCondID) {
		this.orderCondID = orderCondID;
	}

	public String getOrderCond() {
		return orderCond;
	}

	public void setOrderCond(String orderCond) {
		this.orderCond = orderCond;
	}

	
}
