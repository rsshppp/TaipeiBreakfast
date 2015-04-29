package model.bean;

import java.io.Serializable;
import java.sql.Date;

//留言板表
public class MessageBoardBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer messageID;
	private Integer memberID;
	private String context;
	private Date messageDate;
	private String ipAddress;
	
	public MessageBoardBean() {
		
	}

	public Integer getMessageID() {
		return messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
