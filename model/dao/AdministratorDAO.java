package model.dao;

import model.bean.AdministratorBean;

public interface AdministratorDAO {
	public AdministratorBean select(String administratorAcc);
}
