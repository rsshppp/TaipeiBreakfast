package model.service;

import java.util.List;

import model.bean.MealBean;
import model.dao.MealDAO;
import model.dao.MealKindListDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//Gary
public class MealService {
	private MealDAO dao;
	private MealKindListDAO mkdao;

	public void setDao(MealDAO dao) {
		this.dao = dao;
	}

	public void setMkdao(MealKindListDAO mkdao) {
		this.mkdao = mkdao;
	}

	//依ID查詢單一餐點
	public MealBean selectMealByMealID(int mealID){
		return dao.selectOneMeal(mealID);
	}
	//新增餐點
	public boolean addMeal(MealBean bean){
		bean.setMealKindListBean(mkdao.selectOne(bean.getMealKindID()));
		return dao.insert(bean);
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
