package model.dao;

import java.util.List;
import model.bean.OrderSumBean;
import model.bean.ShopBean;

public interface OrderSumDAO {
	//透過ShopBean的ShopID查詢所有"總訂單"，接續查詢個別總訂單的訂單明細
	public List<OrderSumBean> queryOrderSum(ShopBean bean);
	
}
