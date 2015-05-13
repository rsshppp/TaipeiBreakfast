package model.dao;

import java.util.List;

import model.bean.MealBean;
import model.bean.MealKindListBean;
import model.bean.OrderDetailBean;
//建立-Gary
public interface MealDAO {
	//利用訂單明細查詢餐點，餐點裡面有名字
	public MealBean queryMealBean(OrderDetailBean bean);
	//依【店家ID】及【餐點名稱】搜尋詳細資料
	public MealBean selectOneMeal(String mName,int shopID);
	//依【餐點ID】搜尋詳細資料
	public MealBean selectOneMeal(int MealID);
	//依【店家ID】搜尋全部餐點
	public List<MealBean> selectShopMeal(int shopID);
	//依【店家ID】及【餐點種類】搜尋全部餐點
	public List<MealBean> selectShopMeal(int shopID ,int mealKindID);
	//增加餐點
	public boolean insert(MealBean bean);
	//依【ID】更新餐點內容
	public boolean update(MealBean bean);
	//依【ID】更新餐點狀態
	public boolean updateMealStatus(int mealID, boolean mealStatus);
	
	//查詢銷售報表 -利用此餐點表內的資料和關聯對應檔 查詢 此餐點表所對應的餐點總類表(MealKindListBean)-宗鈺
    public abstract  MealKindListBean getMealKindListBean(MealBean bean);
}
