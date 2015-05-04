package model.dao;

import java.util.List;

import model.bean.AdvertisementBean;
//建立-Gary
public interface AdvertisementDAO {
	//依【ID】查詢廣告
	public AdvertisementBean selectOne(int adID);
	//依【店家ID】查詢該店家所有廣告
	public List<AdvertisementBean> selectShopAd(int shopID);
	//查詢所有廣告
	public List<AdvertisementBean> selectAll();
	//新增廣告
	public boolean insert(AdvertisementBean bean);
	//依【ID】來更新【狀態ID】
	public boolean updateStatus(int adID,int asID);
}
