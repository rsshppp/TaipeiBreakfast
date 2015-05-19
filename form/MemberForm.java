package form;

import java.io.File;

public class MemberForm {

	private String memberAcc;
	private String memberPwd;
	private String memberCwd;
	private String memberLastName;
	private String memberFirstName;
	private String memberPhone;
	private String memberTel;
	private String memberEmail;
	private String memberAddr;
	private File memberImage;
	private Boolean memberStatus;
	private Boolean memberSuspend;
	

	public File getMemberImage() {
		return memberImage;
	}
	public void setMemberImage(File memberImage) {
		this.memberImage = memberImage;
	}
	public String getMemberAcc() {
		return memberAcc;
	}
	public void setMemberAcc(String memberAcc) {
		this.memberAcc = memberAcc;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberCwd() {
		return memberCwd;
	}
	public void setMemberCwd(String memberCwd) {
		this.memberCwd = memberCwd;
	}
	public String getMemberLastName() {
		return memberLastName;
	}
	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}
	public String getMemberFirstName() {
		return memberFirstName;
	}
	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public Boolean getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(Boolean memberStatus) {
		this.memberStatus = memberStatus;
	}
	public Boolean getMemberSuspend() {
		return memberSuspend;
	}
	public void setMemberSuspend(Boolean memberSuspend) {
		this.memberSuspend = memberSuspend;
	}
	
}
