package model.service;

import java.util.List;

import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.dao.MemberDAO;
import model.dao.OrderDetailDAO;
import model.dao.OrderSumDAO;

public class MemberFunctionService {
	
	private OrderSumDAO osdao;
	private OrderDetailDAO oddao;
	private MemberDAO mdao;
	
	public void setOsdao(OrderSumDAO osdao) {
		this.osdao = osdao;
	}
	
	public void setOddao(OrderDetailDAO oddao) {
		this.oddao = oddao;
	}
	
	public void setMdao(MemberDAO mdao) {
		this.mdao = mdao;
	}
	
	// 依訂單狀態查詢訂單 - Noah
	public List<OrderSumBean> selectOrderSumByOrderCond(Integer memberID, Integer orderCondID){
		
		return osdao.selectOrderSumByOrderCond(memberID, orderCondID);
	}
	
	// 查詢訂單狀態之後，可查詢此訂單的訂單明細 - Noah
	public List<OrderDetailBean> queryOrderDetails(Integer orderSumID){
		
		return oddao.queryOrderDetails(orderSumID);
	}
	
	// 評價訂單 - Noah
	public boolean updateOrderSum(OrderSumBean bean){
		
		return osdao.updateOrderSum(bean);
		
	}
	
	// 查詢優惠 - Noah
	public MemberBean selectSpecialPrice(Integer memberID){
		
		return mdao.selectSpecialPrice(memberID);
	}
	
	// 獲得優惠 - Noah
	public boolean getSpecialPrice(Integer memberID, Integer specialPriceID){
		
		return mdao.getSpecialPrice(memberID, specialPriceID);
	}
	
	// 使用優惠 - Noah
	public boolean useSpecialPrice(Integer memberID) {
		
		return mdao.useSpecialPrice(memberID);
	}
}
