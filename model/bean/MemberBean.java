package model.Bean;

public class MemberBean {
	private int MemberID;
	private String MemberAcc;
	private String MemberPwd;
	private String MemberLastName;
	private String MemberFirstName;
	private String MemberPhone;
	private String MemberTel;
	private String MemberEmail;
	private String MemberAddr;
	private byte[] MemberImage;
	private String MemberImageName;
	private byte[] MemberStatus;
	private byte MemberSuspend;
	private int DiscountID;
	
	public int getMemberID() {
		return MemberID;
	}
	public void setMemberID(int memberID) {
		MemberID = memberID;
	}
	public String getMemberAcc() {
		return MemberAcc;
	}
	public void setMemberAcc(String memberAcc) {
		MemberAcc = memberAcc;
	}
	public String getMemberPwd() {
		return MemberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		MemberPwd = memberPwd;
	}
	public String getMemberLastName() {
		return MemberLastName;
	}
	public void setMemberLastName(String memberLastName) {
		MemberLastName = memberLastName;
	}
	public String getMemberFirstName() {
		return MemberFirstName;
	}
	public void setMemberFirstName(String memberFirstName) {
		MemberFirstName = memberFirstName;
	}
	public String getMemberPhone() {
		return MemberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		MemberPhone = memberPhone;
	}
	public String getMemberTel() {
		return MemberTel;
	}
	public void setMemberTel(String memberTel) {
		MemberTel = memberTel;
	}
	public String getMemberEmail() {
		return MemberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		MemberEmail = memberEmail;
	}
	public String getMemberAddr() {
		return MemberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		MemberAddr = memberAddr;
	}
	public byte[] getMemberImage() {
		return MemberImage;
	}
	public void setMemberImage(byte[] memberImage) {
		MemberImage = memberImage;
	}
	public String getMemberImageName() {
		return MemberImageName;
	}
	public void setMemberImageName(String memberImageName) {
		MemberImageName = memberImageName;
	}
	public byte[] getMemberStatus() {
		return MemberStatus;
	}
	public void setMemberStatus(byte[] memberStatus) {
		MemberStatus = memberStatus;
	}
	public byte getMemberSuspend() {
		return MemberSuspend;
	}
	public void setMemberSuspend(byte memberSuspend) {
		MemberSuspend = memberSuspend;
	}
	public int getDiscountID() {
		return DiscountID;
	}
	public void setDiscountID(int discountID) {
		DiscountID = discountID;
	}
	
}
