package model.service;

import java.util.List;

import model.bean.MealBean;
import model.dao.MealDAO;
import model.dao.MealKindListDAO;
import model.dao.ShopDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//Gary
public class MealService {
	private MealDAO dao;
	private MealKindListDAO mkdao;
	private ShopDAO shopdao;

	public void setDao(MealDAO dao) {
		this.dao = dao;
	}

	public void setMkdao(MealKindListDAO mkdao) {
		this.mkdao = mkdao;
	}

	public void setShopdao(ShopDAO shopdao) {
		this.shopdao = shopdao;
	}

	//依ID查詢單一餐點
	public MealBean selectMealByMealID(int mealID){
		return dao.selectOneMeal(mealID);
	}
	//新增餐點
	public boolean addMeal(MealBean bean){
		if(bean.getMealName()!=null&&bean.getMealName().trim().length()!=0
			&&bean.getPrice()!=null&&bean.getPrice()!=0
			&&bean.getShopID()!=null&&bean.getShopID()!=0
			&&bean.getMealKindID()!=null&&bean.getMealKindID()!=0){
			bean.setMealStatus(true);	
			bean.setMealKindListBean(mkdao.selectOne(bean.getMealKindID()));
			bean.setShopBean(shopdao.select(bean.getShopID()));
			return dao.insert(bean);
		}
		return false;
	}
	//修改餐點內容
	public boolean modifyMeal(MealBean bean){
		bean.setMealKindListBean(mkdao.selectOne(bean.getMealKindID()));
		return dao.update(bean);
	}
	//修改餐點狀態
	public boolean modifyMealStatus(int mealID, boolean mealStatus){
		return dao.updateMealStatus(mealID, mealStatus);
	}
	//查詢店家菜單
	public List<MealBean> selectShopMeal(int shopID){
		return dao.selectShopMeal(shopID);
	}
}
