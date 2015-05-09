package model.dao;

import java.util.List;

import model.bean.OrderSumBean;
import model.bean.ShopBean;

import java.sql.Date;

public interface OrderSumDAO {
	//透過ShopBean的ShopID查詢所有"總訂單"，接續查詢個別總訂單的訂單明細 - chun-ting
	public List<OrderSumBean> queryOrderSum(ShopBean bean);
	
	//新增一筆訂單需要取得店鋪ID、會員ID、總價格、訂單取餐時間 - Noah
	public abstract boolean insertOrderSum(OrderSumBean bean);
	
	//進行評價需要取得總訂單ID、會員給予星數、會員給予評價 - Noah
	public abstract boolean updateOrderSum(OrderSumBean bean);
	
	//更改訂單狀態 - Noah
	public abstract boolean changeOrderCond(Integer orderSumID);
	
	//刪除一筆訂單需要總訂單ID - Noah
	public abstract boolean deleteOrderSum(Integer orderSumID);
	
	//查詢全部屬於某會員的訂單需要會員ID - Noah
	public abstract List<OrderSumBean> selectAllOrderSum(Integer memberID);
	
	//使用訂單狀態查詢屬於某會員的訂單需要會員ID、訂單狀態 - Noah
	public abstract List<OrderSumBean> selectOrderSumByOrderCond(Integer memberID, Integer orderCondID);

	//店家更改訂單狀狀，例如：接受訂單 -廷
	public abstract boolean updateOrderCond(OrderSumBean bean);

	//傳入page值 , 每個page抓10筆資料  (-.-)*杜
	public List<OrderSumBean> queryOrderSumByTime(int page);
	
}