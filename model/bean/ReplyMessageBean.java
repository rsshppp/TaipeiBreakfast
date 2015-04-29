package model.bean;

import java.io.Serializable;
import java.sql.Date;

//留言板回覆表
public class ReplyMessageBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer replyMessageID;
	private Integer messageID;
	private Integer memberID;
	private String replyMessage;
	private Date replyDate;
	private String ipAddress;
	
	public ReplyMessageBean() {
		
	}

	public Integer getReplyMessageID() {
		return replyMessageID;
	}

	public void setReplyMessageID(Integer replyMessageID) {
		this.replyMessageID = replyMessageID;
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

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
