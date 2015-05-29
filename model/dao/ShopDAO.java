package model.dao;

import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;


//更多細節請看其實作類別(model.dao.imp.ShopDAOHibernate)
public interface ShopDAO {    

	//(-.-)*杜
	public abstract List<ShopBean> selectAK(String shopArea,String keyword);
	public abstract List<ShopBean> selectKeyword(String keyword);
	public abstract List<ShopBean> selectArea(String shopArea);
	public abstract List<ShopBean> allowNeedsShop();
	public abstract boolean allowShop(int ShopID);
	public abstract boolean notAllowShop(int ShopID);

	//Gary
	//用店鋪編號得到"該筆"店鋪所有資料--宗鈺
	public abstract ShopBean select(Integer shopID);
	
	//很擔心拿不到shopID(shopID依賴session得到),"以防萬一"用此方法從資料庫獲取店鋪資料--宗鈺
	public abstract ShopBean selectByPhone(String shopPhone);
		
	//查"所有"店鋪資料--宗鈺
	public abstract List<ShopBean> selectAll();

	//利用賣方ID查該賣方所擁有全部店鋪資料--宗鈺
	public abstract List<ShopBean> getShops(Integer ownID);

	//新增一筆店鋪資料                           
	//使用boolean是因為sql系統有預設值,在commit前無法取得新的bean資料
	//經測試,insert要commit才入資料庫,update在update方法已更動資料庫--宗鈺
	public abstract boolean insert(ShopBean shopBean);

	//更改店鋪多項欄位資料--宗鈺
	public abstract ShopBean update(ShopBean shopBean);

	//更改店鋪狀態ID--宗鈺
	public abstract boolean changeShopCondID(Integer shopCondID,Integer shopID);

	//為後台管理停權所使用,可以停權,也可以取消停權--宗鈺
	public abstract boolean suspendOrCancel(Integer shopID);
	
	//更改某間店家營業時間	-chunting
	public boolean updateBusinessTime(ShopBean bean);
	
	//更改某間店家營業註解 -chunting
	public boolean updateBusinessTimeNote(ShopBean bean);
	
	//測試透過Owner查詢店鋪、店鋪的餐點
	public List<ShopBean> queryShops(OwnerBean bean);
	
	//根據shopArea找商家資料(for historyMap usage)--宗鈺
	public List<ShopBean> selectShopArea(String shopArea);	
}