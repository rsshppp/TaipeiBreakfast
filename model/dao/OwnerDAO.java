package model.dao;

import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;

public interface OwnerDAO {   //更多細節請看其實作類別(model.dao.imp.OwnerHibernateDAO)
	
    //用賣方帳號得到"該筆"賣方所有資料, 之後可business service可用此方法
	public abstract OwnerBean select(String ownerAccount);
    
	//查"所有"賣方資料
	public abstract List<OwnerBean> selectAll();
	
    //利用賣方ID查該賣方所擁有全部店鋪資料
	public abstract List<ShopBean> getShops(Integer ownID);

	//新增一筆賣方資料
	public abstract OwnerBean insert(OwnerBean ownerBean);

	//更改賣方資料
	public abstract OwnerBean update(OwnerBean ownerBean);
	
    //賣方刪帳號時所用的方法,但實作是update改賣方的ownStatus而已
	public abstract boolean delete(Integer ownID);

}