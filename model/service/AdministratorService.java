package model.service;

import model.bean.AdministratorBean;
import model.dao.AdministratorDAO;

public class AdministratorService {
	private AdministratorDAO admindao;

	public void setAdmindao(AdministratorDAO admindao) {
		this.admindao = admindao;
	}
	
	public AdministratorBean login(String user,String password){
		if(user!=null&&user.trim().length()!=0&&password!=null&&password.trim().length()!=0){
			AdministratorBean bean= admindao.select(user);
			if(bean!=null){
				if(password.equals(bean.getAdministratorPwd())){
					return bean;
				}
			}
		}
		return null;
	}
}
