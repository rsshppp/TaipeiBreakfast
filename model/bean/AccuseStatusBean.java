package model.bean;

import java.io.Serializable;

//檢舉狀態表
public class AccuseStatusBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer accuseStatusID;
	private String accuseStatus;
	
	public AccuseStatusBean() {
		
	}

	public Integer getAccuseStatusID() {
		return accuseStatusID;
	}

	public void setAccuseStatusID(Integer accuseStatusID) {
		this.accuseStatusID = accuseStatusID;
	}

	public String getAccuseStatus() {
		return accuseStatus;
	}

	public void setAccuseStatus(String accuseStatus) {
		this.accuseStatus = accuseStatus;
	}
}
