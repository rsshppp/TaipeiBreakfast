package model.service;

import java.util.List;

import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.dao.OrderSumDAO;

public class OrderSumService {
	private OrderSumDAO orderSumDAO;

	public OrderSumDAO getOrderSumDAO() {
		return orderSumDAO;
	}

	public void setOrderSumDAO(OrderSumDAO orderSumDAO) {
		this.orderSumDAO = orderSumDAO;
	}
	
	//店家查詢所有該店家的訂單	-俊廷
	public List<OrderSumBean> queryOrderSum(ShopBean bean){
		return orderSumDAO.queryOrderSum(bean);
	}
	
	//店家更改訂單狀態	-俊廷
	public boolean updateOrderCond(OrderSumBean bean){
		OrderSumBean querybean = orderSumDAO.queryOneOrderSum(bean.getOrderSumID());
		if(querybean.getOrderCondID()==4 || bean.getOrderCondID()==1){
			return false;
		}
		return orderSumDAO.updateOrderCond(bean);
	}
	//會員新增一筆訂單	-俊廷
	public boolean insertOrder(OrderSumBean bean){
		return orderSumDAO.insertOrder(bean);
	}
}
