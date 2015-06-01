package model.service;

import java.sql.Date;
import java.util.List;

import model.bean.MealBean;
import model.bean.MemberBean;
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
	
	//會員查詢優惠券
	public SpecialPriceBean querySpecialPrice(MemberBean bean){
		SpecialPriceBean sbean = specialPriceDAO.querySpecialPrice(bean);
		if(sbean==null){
			return null;
		}
		java.sql.Date nowDate = new Date(System.currentTimeMillis());
		java.sql.Date sDate = sbean.getStartDate();
		java.sql.Date eDate = sbean.getEndDate();
		if(nowDate.compareTo(sDate) >=0 && nowDate.compareTo(eDate) <=0){
			SpecialPriceBean spbean = new SpecialPriceBean();
			spbean.setMealID(sbean.getMealID());
			spbean.setSpecialPrice(sbean.getSpecialPrice());
			MealBean mbean = new MealBean();
			spbean.setMealBean(mbean);
			spbean.getMealBean().setPrice(sbean.getMealBean().getPrice());
			spbean.getMealBean().setMealName(sbean.getMealBean().getMealName());
			return spbean;
		}
		return null;
	}
}
