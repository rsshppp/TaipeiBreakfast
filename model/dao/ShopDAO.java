package model.dao;

import java.util.List;

import model.bean.ShopBean;

public interface ShopDAO {    //更多細節請看其實作類別(model.dao.imp.ShopHibernateDAO)

	//(-.-)*杜
	public abstract List<ShopBean> selectShop(String keyword,int shopArea,int shopID);
	
	//用店鋪名字得到"該筆"店鋪所有資料
	public abstract ShopBean select(String shopName);

	//查"所有"店鋪資料
	public abstract List<ShopBean> selectAll();

	//利用賣方ID查該賣方所擁有全部店鋪資料
	public abstract List<ShopBean> getShops(Integer ownID);

	//新增一筆店鋪資料                           //使用boolean是因為sql系統有預設值,在commit前無法取得新的bean資料,經測試,insert要commit才入資料庫,update在update方法已更動資料庫
	public abstract boolean insert(ShopBean shopBean);

	//更改店鋪多項欄位資料
	public abstract ShopBean update(ShopBean shopBean);

	//更改店鋪狀態ID
	public abstract boolean changeShopCondID(ShopBean shopBean);

	//為後台管理停權所使用,可以停權,也可以取消停權
	public abstract boolean suspendOrCancel(String shopName);

}