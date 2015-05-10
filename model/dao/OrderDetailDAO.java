package model.dao;

import java.util.List;

import model.bean.MealBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;

public interface OrderDetailDAO {
//	//利用個別總訂單查詢訂單明細，接著查詢餐點名字
//	public List<OrderDetailBean> queryOrderDetails(OrderSumBean bean);
	
	//利用個別總訂單查詢訂單明細，接著查詢餐點名字                                             
	public List<OrderDetailBean> queryOrderDetails(Integer orderSumID);
		                                             //宗鈺-修改介面參數 <--請原作者確認無誤後把此段刪除,謝謝!
	
	//查詢交易歷史記錄 -利用此訂單明細內的資料和關聯對應檔 查詢 此訂單明細所對應的MealBean-宗鈺
	public abstract  MealBean getMealBean(OrderDetailBean bean);
	
}                           
