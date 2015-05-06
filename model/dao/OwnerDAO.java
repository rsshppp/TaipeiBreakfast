package model.dao;

import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;

public interface OwnerDAO {    //by宗鈺

	//用賣方帳號得到"該筆"賣方所有資料, 之後可business service可用此方法         //login可用此方法  //已過濾掉status為false之賣家
	public abstract OwnerBean select(String ownerAccount);

	//查"所有"賣方資料
	public abstract List<OwnerBean> selectAll();

	//利用賣方ID查該賣方所擁有全部店鋪資料
	public abstract List<ShopBean> getShops(Integer ownID);

	//新增一筆賣方資料                                   //使用boolean是因為sql系統有預設值,在commit前無法取得新的bean資料,經測試,insert要commit才入資料庫,update在update方法已更動資料庫
	public abstract boolean insert(OwnerBean ownerBean);

	//更改賣方多項欄位資料        <--用此已足夠,其他update是以防萬一時用(於前端設計完若沒用到可考慮刪掉)
	public abstract OwnerBean update(OwnerBean ownerBean);

	//update Name相關欄位資料
	public abstract OwnerBean updateName(String ownLastName,
			String ownFirstName, String ownAcc);

	//update單一(Email欄位資料)
	public abstract OwnerBean updateEmail(String ownEmail, String ownAcc);

	//update單一(密碼欄位資料)
	public abstract OwnerBean updatePwd(String ownPwd, String ownAcc);

	//賣方刪帳號時所用的方法,但實作是update改賣方的ownStatus而已
	public abstract boolean delete(String ownAcc);
	
	//為後台管理停權所使用,可以停權,也可以取消停權
	public abstract boolean suspendOrCancel(String ownAcc);

}