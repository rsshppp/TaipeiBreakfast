package model.dao;

import model.bean.OrderSumBean;

public interface OrderCondDAO {
	//店家查詢總訂單的資料後想要修改訂單狀態時使用的方法，需要OrderSumID
	public boolean updateOrderCond(OrderSumBean bean);
}
