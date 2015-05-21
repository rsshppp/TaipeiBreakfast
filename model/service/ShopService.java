package model.service;


import java.util.List;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.ShopDAO;

public class ShopService {
    private ShopDAO shopDAO;
    
	public void setShopDAO(ShopDAO shopDAO) {
		this.shopDAO = shopDAO;
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

}
