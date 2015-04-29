package model.bean;

import java.io.Serializable;

//管理者表
public class AdministratorBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer administratorID;
	private String administratorAcc;
	private String administratorPwd;
	private Integer administratorPermissionID;
	
	public AdministratorBean() {	

	}

	public Integer getAdministratorID() {
		return administratorID;
	}

	public void setAdministratorID(Integer administratorID) {
		this.administratorID = administratorID;
	}

	public String getAdministratorAcc() {
		return administratorAcc;
	}

	public void setAdministratorAcc(String administratorAcc) {
		this.administratorAcc = administratorAcc;
	}

	public String getAdministratorPwd() {
		return administratorPwd;
	}

	public void setAdministratorPwd(String administratorPwd) {
		this.administratorPwd = administratorPwd;
	}

	public Integer getAdministratorPermissionID() {
		return administratorPermissionID;
	}

	public void setAdministratorPermissionID(Integer administratorPermissionID) {
		this.administratorPermissionID = administratorPermissionID;
	}
}
