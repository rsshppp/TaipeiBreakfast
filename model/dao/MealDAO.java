package model.dao;

import java.util.List;

import model.bean.MealBean;
import model.bean.OrderDetailBean;
//建立-Gary
public interface MealDAO {
	//利用訂單明細查詢餐點，餐點裡面有名字
	public MealBean queryMealBean(OrderDetailBean bean);
	//依【店家ID】及【餐點名稱】搜尋詳細資料
	public MealBean selectOneMeal(String mName,int shopID);
	//依【店家ID】搜尋全部餐點
	public List<MealBean> selectShopMeal(int shopID);
	//增加餐點
	public boolean insert(MealBean bean);
	//依【ID】更新餐點內容
	public boolean update(MealBean bean);
	//依【ID】更新餐點狀態
	public boolean updateMealStatus(MealBean bean);
	
}
