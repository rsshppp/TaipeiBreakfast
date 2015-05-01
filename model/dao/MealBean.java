package model.dao;

import model.bean.OrderDetailBean;

public interface MealBean {
	//利用訂單明細查詢餐點，餐點裡面有名字
	public MealBean queryMealBean(OrderDetailBean bean);
}
