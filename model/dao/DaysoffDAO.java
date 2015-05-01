package model.dao;

import model.bean.DaysOffBean;
import model.bean.ShopBean;

public interface DaysoffDAO {
	public boolean insertDaysOff(DaysOffBean bean);
	public boolean updateDaysOff(DaysOffBean bean);
	public boolean deleteDaysOff(DaysOffBean bean);
	public boolean queryDaysOff(ShopBean bean);
}
