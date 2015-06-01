package form;

import java.io.Serializable;

public class OwnerForm implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
    private Integer ownID;
    private String ownAcc;
    private String ownPwd;
    private String ownEmail;
    private String ownLastName;
    private String ownFirstName;
    
	public Integer getOwnID() {
		return ownID;
	}
	public void setOwnID(Integer ownID) {
		this.ownID = ownID;
	}
	public String getOwnAcc() {
		return ownAcc;
	}
	public void setOwnAcc(String ownAcc) {
		this.ownAcc = ownAcc;
	}
	public String getOwnPwd() {
		return ownPwd;
	}
	public void setOwnPwd(String ownPwd) {
		this.ownPwd = ownPwd;
	}
	public String getOwnEmail() {
		return ownEmail;
	}
	public void setOwnEmail(String ownEmail) {
		this.ownEmail = ownEmail;
	}
	public String getOwnLastName() {
		return ownLastName;
	}
	public void setOwnLastName(String ownLastName) {
		this.ownLastName = ownLastName;
	}
	public String getOwnFirstName() {
		return ownFirstName;
	}
	public void setOwnFirstName(String ownFirstName) {
		this.ownFirstName = ownFirstName;
	}
	
}
