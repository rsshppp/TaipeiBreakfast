package model.dao;

import java.sql.Date;
import java.util.List;

import model.bean.DaysoffBean;
import model.bean.ShopBean;

public interface DaysoffDAO {
	public boolean insertDaysoff(DaysoffBean bean);
	public boolean updateDaysoff(DaysoffBean bean);
	public boolean deleteDaysoff(DaysoffBean bean);
	public List<DaysoffBean> queryDaysoff(ShopBean bean);
}
