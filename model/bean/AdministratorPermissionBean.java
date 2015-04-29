package model.bean;

import java.io.Serializable;

//管理者權限表
public class AdministratorPermissionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer administratorPermissionID;
	private String administratorPermissionStatus;
	
	public AdministratorPermissionBean() {
		
	}

	public Integer getAdministratorPermissionID() {
		return administratorPermissionID;
	}

	public void setAdministratorPermissionID(Integer administratorPermissionID) {
		this.administratorPermissionID = administratorPermissionID;
	}

	public String getAdministratorPermissionStatus() {
		return administratorPermissionStatus;
	}

	public void setAdministratorPermissionStatus(
			String administratorPermissionStatus) {
		this.administratorPermissionStatus = administratorPermissionStatus;
	}
}
