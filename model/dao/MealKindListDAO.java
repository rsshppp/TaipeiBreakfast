package model.dao;

import java.util.List;
import java.util.Set;

import model.bean.MealKindListBean;
import model.bean.ShopBean;

public interface MealKindListDAO {
	//依【ID】搜尋種類
	public MealKindListBean selectOne(int mklID);
	//搜尋全部種類
	public List<MealKindListBean> selectAll();
	//新增種類
	public boolean insert(MealKindListBean bean);
	//依【ID】更新種類 
	public boolean update(MealKindListBean bean);
    //依【店家ID】 搜尋餐點種類	-俊廷
	public Set<MealKindListBean> queryMealKindList(ShopBean bean);
}
