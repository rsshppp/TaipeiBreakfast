package model.service;

import java.util.List;
//Gary
import model.bean.AdvertisementStatusBean;
import model.dao.AdvertisementStatusDAO;

public class AdvertisementStatusService {
	private AdvertisementStatusDAO dao;

	public void setDao(AdvertisementStatusDAO dao) {
		this.dao = dao;
	}
	
	//查詢所有狀態
	public List<AdvertisementStatusBean> selectAllStatus(){
		return dao.selectAll();
	}
}
