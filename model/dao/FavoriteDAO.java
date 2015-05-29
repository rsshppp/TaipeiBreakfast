package model.dao;

import java.util.List;

import model.bean.FavoriteBean;
import model.bean.MemberBean;
import model.bean.MealBean;
import model.bean.OrderDetailBean;

;
//by Steven
public interface FavoriteDAO {
	// 新增一筆favorite我的最愛
	public abstract boolean Create(FavoriteBean bean);

	// 查詢favorite我的最愛列表by memberID
	public abstract List<FavoriteBean> ReadAll(int memberID);

	// 刪除一筆favorite我的最愛by favoriteID
	public abstract boolean Delete(int favoriteID);
	
	// 得到Meal的完整資料
	public abstract  MealBean getMealBean(FavoriteBean bean);
	
	public abstract MemberBean getMemberBean(FavoriteBean bean);
}
