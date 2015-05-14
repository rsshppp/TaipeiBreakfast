package model.service;

import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.bean.SpecialPriceBean;
import model.dao.ShopDAO;
import model.dao.SpecialPriceDAO;

public class SpecialPriceService {
	private ShopDAO shopDAO;
	private SpecialPriceDAO specialPriceDAO;
	
	public ShopDAO getShopDAO() {
		return shopDAO;
	}
	public void setShopDAO(ShopDAO shopDAO) {
		this.shopDAO = shopDAO;
	}
	public SpecialPriceDAO getSpecialPriceDAO() {
		return specialPriceDAO;
	}
	public void setSpecialPriceDAO(SpecialPriceDAO specialPriceDAO) {
		this.specialPriceDAO = specialPriceDAO;
	}
	
	//展現所有店家和該店家餐點供賣方查詢，方便賣方填入優惠券
	public List<ShopBean> queryShops(OwnerBean bean){
		return shopDAO.queryShops(bean);
	}
	
	//查詢賣家所有優惠券
	public List<SpecialPriceBean> querySpecialPrice(OwnerBean bean){
		return specialPriceDAO.querySpecialPrice(bean);
	}
	
	//店家新增優惠券
	public boolean insertSpecialPrice(SpecialPriceBean bean){
		return specialPriceDAO.insertSpecialPrice(bean);
	}
	
	//店家刪除優惠券
	public boolean updateSpecialPrice(SpecialPriceBean bean){
		return specialPriceDAO.updateSpecialPrice(bean);
	}
	
	//店家更新優惠券
	public boolean deleteSpecialPrice(SpecialPriceBean bean){
		return specialPriceDAO.deleteSpecialPrice(bean);
	}
	
}
