package model.dao;

import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.bean.SpecialPriceBean;

public interface SpecialPriceDAO {
	//SpecialBean 去做新增、刪除、修改
	public boolean insertSpecialPrice(SpecialPriceBean bean);
	public boolean updateSpecialPrice(SpecialPriceBean bean);
	public boolean deleteSpecialPrice(SpecialPriceBean bean);
	//透過ShopBean 查詢該店家優惠券(ShopBean最少有ShopID)
	public List<SpecialPriceBean> querySpecialPrice(ShopBean bean);
	//查詢賣家所有的優惠券(所有店鋪的)	chunting
	public List<SpecialPriceBean> querySpecialPrice(OwnerBean bean);
}
