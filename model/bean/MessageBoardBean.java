package model.bean;

import java.io.Serializable;
import java.sql.Timestamp;

//留言板表
public class MessageBoardBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer messageID;
	private Integer memberID;
	private String context;
	private Timestamp messageDate;
	private String ipAddress;
	private MemberBean memberBean;
	
	public MessageBoardBean() {
		
	}
	
	@Override
	public String toString() {
		return "messageID="+messageID+"，memberID="+memberID+"，context="
				+context+"，messageDate="+messageDate+"，ipAddress="+ipAddress;
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

	public Timestamp getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Timestamp messageDate) {
		this.messageDate = messageDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	
}
