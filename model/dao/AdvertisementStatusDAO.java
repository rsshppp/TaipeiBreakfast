package model.dao;

import java.util.List;

import model.bean.AdvertisementBean;
import model.bean.AdvertisementStatusBean;
//建立-Gary
public interface AdvertisementStatusDAO {
	//依【ID】查詢廣告狀態
	public AdvertisementStatusBean selectOne(int asID);
	//搜尋全部狀態
	public List<AdvertisementStatusBean> selectAll();
	//新增狀態
	public boolean Insert(AdvertisementStatusBean bean);
	//依【ID】更新廣告狀態
	public boolean update(AdvertisementStatusBean bean);
}
