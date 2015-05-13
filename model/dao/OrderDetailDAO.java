package model.dao;

import java.util.List;

import model.bean.MealBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;

public interface OrderDetailDAO {

	//利用個別總訂單查詢訂單明細，接著查詢餐點名字 - 沛勳
	public List<OrderDetailBean> queryOrderDetails(Integer orderSumID);

	//查詢交易歷史記錄 -利用此訂單明細內的資料和關聯對應檔，查詢此訂單明細所對應的 MealBean - 宗鈺
	public abstract  MealBean getMealBean(OrderDetailBean bean);
	
	//新增訂單明細 - Noah
	public abstract boolean insertOrderDetail(OrderDetailBean bean);
	
	//更改訂單明細 - Noah
	public abstract boolean changeOrderDetail(OrderDetailBean bean);
		
	//刪除一筆訂單明細需要訂單明細ID - Noah
	public abstract boolean deleteOrderDetail(Integer orderDetailID);
}                           
