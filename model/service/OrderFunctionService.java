package model.service;

import java.util.List;

import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.dao.OrderDetailDAO;
import model.dao.OrderSumDAO;

public class OrderFunctionService {
	
	private OrderSumDAO osdao;
	private OrderDetailDAO oddao;
	
	public void setOsdao(OrderSumDAO osdao) {
		this.osdao = osdao;
	}
	public void setOddao(OrderDetailDAO oddao) {
		this.oddao = oddao;
	}
	
	// 老樣子提示 - Noah
	public List<OrderDetailBean> querySameOld(Integer memberID){
		
		OrderSumBean bean = osdao.selectLastOrderSum(memberID);
		return oddao.queryOrderDetails(bean.getOrderSumID());
	}
	
	// 老樣子訂購早餐 - Noah
	public boolean orderSameOld(OrderSumBean bean){
		
		return osdao.insertOrderSum(bean);
		
	}
}
