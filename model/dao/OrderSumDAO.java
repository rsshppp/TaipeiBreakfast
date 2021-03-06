﻿package model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.bean.deliverValuesOnly.HistoryRecordBean;

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
	
	//搜尋最後一筆會員的訂單資訊(老樣子) - Noah
	public abstract OrderSumBean selectLastOrderSum(Integer memberID);
	
	//店家更改訂單狀狀，例如：接受訂單 -廷
	public abstract boolean updateOrderCond(OrderSumBean bean);
	
    //傳入page值 , 每個page抓10筆資料  (-.-)*杜
	public List<OrderSumBean> queryOrderSumByTime(int page);

	//新增一筆來自會員的訂單
	public abstract boolean insertOrder(OrderSumBean sbean);

	//利用訂單編號查詢單一筆訂單	-chunting (for updateOrderCond of OrderSumService)
	public OrderSumBean queryOneOrderSum(Integer OrderSumID);
	
	//利用店鋪查詢OrderSum後進行分頁，需要給兩個分頁參數	-chunting
	public List<OrderSumBean> queryOrderSum(ShopBean bean, Integer page, Integer pagesize);
	
	//查詢交易歷史記錄 -利用"店鋪ID和訂單狀態"查詢某店鋪全部的總訂單表-宗鈺
	public abstract List<OrderSumBean> selectOrderSumByShopID(Integer shopID, Integer orderCondID);
	
	//查詢交易歷史記錄 -利用此總訂單內的資料和關聯對應檔 查詢 此總訂單所有的訂單明細-宗鈺 (關聯相依性已高,這反而多餘)
	public abstract Set<OrderDetailBean> selectOrderDetails(OrderSumBean bean);
	
	//查詢交易歷史記錄 -利用此總訂單內的資料和關聯對應檔 查詢 此總訂單所對應的會員資料(MemberBean)-宗鈺 (關聯相依性已高,這反而多餘)
	public abstract  MemberBean getMemberBean(OrderSumBean bean);
	
	//查詢交易歷史記錄 -利用此總訂單內的資料和關聯對應檔 查詢 此總訂單所對應的店鋪資料(ShopBean)-宗鈺 (關聯相依性已高,這反而多餘)
	public abstract  ShopBean getShopBean(OrderSumBean bean);
	
	//查詢交易歷史記錄 -因為此方法造成紅字的人請麻煩一下找我解決,很抱歉造成不便--宗鈺 (這應該ok)
    public abstract List<HistoryRecordBean> selectHistoryRecord(Integer shopID, Integer orderCondID);
    
    //特定店鋪日報表--宗鈺
    public abstract List<Object[]> getDailyReport(Integer shopID,Integer year,Integer month,Integer day)throws ParseException;
    
    //特定店鋪月報表--宗鈺
    public abstract List<Object[]> getMonthlyReport(Integer shopID,Integer year,Integer month)throws ParseException;
	
    //特定店鋪當日不同時段報表--宗鈺
    public abstract List<Object> getTimeReport(Integer shopID,Integer year,Integer month,Integer day)throws ParseException;
    
    //特定店鋪特定時段報表--宗鈺
    public abstract List<Object[]> getDetailTimeReport(Integer shopID,Integer year,Integer month,Integer day,Integer hour)throws ParseException;
    
    //特定店鋪年營收報表--宗鈺
    public abstract List<Object> getYearly(Integer shopID,Integer year)throws ParseException;
    
    //特定店鋪年營收細節--宗鈺
    public abstract List<Object> getYearlyDetail(Integer shopID,Integer year)throws ParseException;
}