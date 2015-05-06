package model.service;

import java.util.List;

import model.bean.AdvertisementBean;
import model.dao.AdvertisementDAO;
import model.dao.AdvertisementStatusDAO;
import model.dao.ShopDAO;
//Gary
public class AdvertisementService {
	private AdvertisementDAO dao;
	private ShopDAO shopdao;
	private AdvertisementStatusDAO aStatusdao;
	public void setDao(AdvertisementDAO dao) {
		this.dao = dao;
	}
	public void setShopdao(ShopDAO shopdao) {
		this.shopdao = shopdao;
	}
	public void setaStatusdao(AdvertisementStatusDAO aStatusdao) {
		this.aStatusdao = aStatusdao;
	}
	//查詢所有廣告
	public List<AdvertisementBean> selectAllAd(){
		return dao.selectAll();
	}
	//查詢特定店家廣告
	public List<AdvertisementBean> selectShopAd(int shopID){
		return dao.selectShopAd(shopID);
	}
	//查詢特定廣告
	public AdvertisementBean selectAd(int adID){
		return dao.selectOne(adID);
	}
	//新增廣告
	public boolean addAd(AdvertisementBean bean){
		bean.setAdvertisementStatusBean(aStatusdao.selectOne(bean.getAdvertisementStatusID()));
		bean.setShopBean(shopdao.select(bean.getShopID()));
		return dao.insert(bean);
	}
	//更改廣告狀態
	public boolean changeAdStatus(int adID,int adStatusID){
		return dao.updateStatus(adID, aStatusdao.selectOne(adStatusID));
	}
}
