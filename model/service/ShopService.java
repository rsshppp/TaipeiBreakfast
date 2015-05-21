package model.service;


import java.util.List;
import model.bean.DaysoffBean;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.DaysoffDAO;
import model.dao.ShopDAO;

public class ShopService {
    private ShopDAO shopDAO;
	private DaysoffDAO daysoffDAO;
	public void setShopDAO(ShopDAO shopDAO) {
		this.shopDAO = shopDAO;
	}

	public void setDaysoffDAO(DaysoffDAO daysoffDAO) {
		this.daysoffDAO = daysoffDAO;
	}
	//使用店鋪ID查詢該店鋪資料
	public ShopBean select(Integer shopID){
		if(shopID!=null && shopID!=0){
			return shopDAO.select(shopID);
		}
		return null;
	} 

	//利用賣家ID 查詢 其所有店鋪資料
	public List<ShopBean> getShops(OwnerBean ownerBean){
		if(ownerBean!=null && ownerBean.getOwnID()!=0){
			return shopDAO.getShops(ownerBean.getOwnID());
		}
		return null;
	}
	
	//新增店鋪資料
	public boolean insert(ShopBean shopBean){
		if(shopBean!=null){
		   if(shopDAO.insert(shopBean)){
			   return true;
		   }
		}
		return false;
	}
	
	//修改店鋪資料
	public ShopBean update(ShopBean shopBean){
		if(shopBean!=null){
			return shopDAO.update(shopBean);
		}
		return null;
	}
	
	//修改店鋪狀態
	public boolean changeShopCondID(Integer newShopCondID,ShopBean shopBean){
		if(shopBean!=null && newShopCondID!=0 && shopBean.getShopID()!=0){
			if(shopDAO.changeShopCondID(newShopCondID, shopBean.getShopID())){
				return true;
			}
		}
		return false;
	}
	
	//為後台管理停權所使用,可以停權,也可以取消停權
	public boolean suspendOrCancel(ShopBean shopBean){
		if(shopBean!=null && shopBean.getShopID()!=0){
		   if(shopDAO.suspendOrCancel(shopBean.getShopID())){
			   return true;
		   }	
		}
		return false;
	}

	// 查詢營業時間和休假日 -chunting
	public ShopBean queryShopBusinessTimeAndDaysoff(ShopBean bean) {
		return shopDAO.select(bean.getShopID());
	}

	// 更新營業時間 -chunting
	public boolean updateShopBusinessTime(ShopBean bean) {
		return shopDAO.updateBusinessTime(bean);
	}

	// 更新營業註解 -chunting
	public boolean updateShopBusinessTimeNote(ShopBean bean) {
		return shopDAO.updateBusinessTimeNote(bean);
	}

	//新增休假日-chunting
	public boolean insertDaysoff(DaysoffBean bean) {
		return daysoffDAO.insertDaysoff(bean);
	}

	//刪除休假日-chunting
	public boolean deleteDaysoff(DaysoffBean bean) {
		return daysoffDAO.deleteDaysoff(bean);
	}

}
