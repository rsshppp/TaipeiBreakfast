package model.bean;

import java.io.Serializable;
import java.sql.Date;

public class AccuseBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer accuseID;
	private Integer memberID;
	private String accuseSub;
	private String accuseContent;
	private Integer accuseStatusID;
	private Date accuseTime;
	
	public AccuseBean() {
		
	}

	public Integer getAccuseID() {
		return accuseID;
	}

	public void setAccuseID(Integer accuseID) {
		this.accuseID = accuseID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public String getAccuseSub() {
		return accuseSub;
	}

	public void setAccuseSub(String accuseSub) {
		this.accuseSub = accuseSub;
	}

	public String getAccuseContent() {
		return accuseContent;
	}

	public void setAccuseContent(String accuseContent) {
		this.accuseContent = accuseContent;
	}

	public Integer getAccuseStatusID() {
		return accuseStatusID;
	}

	public void setAccuseStatusID(Integer accuseStatusID) {
		this.accuseStatusID = accuseStatusID;
	}

	public Date getAccuseTime() {
		return accuseTime;
	}

	public void setAccuseTime(Date accuseTime) {
		this.accuseTime = accuseTime;
	}
}
