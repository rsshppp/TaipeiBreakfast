package model.dao;

import java.util.List;

import model.bean.MealKindListBean;

public interface MealKindListDAO {
	//依【ID】搜尋種類
	public MealKindListBean selectOne(int mklID);
	//搜尋全部種類
	public List<MealKindListBean> selectAll();
	//新增種類
	public boolean insert(MealKindListBean bean);
	//依【ID】更新種類 
	public boolean update(MealKindListBean bean);
}
