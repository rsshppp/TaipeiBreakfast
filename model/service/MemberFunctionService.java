package model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.bean.MealBean;
import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.bean.SpecialPriceBean;
import model.dao.MemberDAO;
import model.dao.OrderDetailDAO;
import model.dao.OrderSumDAO;
import model.dao.SpecialPriceDAO;

public class MemberFunctionService {

	private OrderSumDAO osdao;
	private OrderDetailDAO oddao;
	private MemberDAO mdao;
	private SpecialPriceDAO spdao;

	public void setOsdao(OrderSumDAO osdao) {
		this.osdao = osdao;
	}

	public void setOddao(OrderDetailDAO oddao) {
		this.oddao = oddao;
	}

	public void setMdao(MemberDAO mdao) {
		this.mdao = mdao;
	}

	public void setSpdao(SpecialPriceDAO spdao) {
		this.spdao = spdao;
	}
	
	//登入 by Steven
			public MemberBean login(String user,String password){
				if(user!=null&&user.trim().length()!=0&&password!=null&&password.trim().length()!=0){
					MemberBean bean= mdao.selectMemberByMail(user);
					System.out.println(bean.getMemberPwd());
					if(bean!=null){
						if(password.equals(bean.getMemberPwd())){
							return bean;
						}
					}
				}
				return null;
			}
	
	//停權<==>復權 by Steven
			public boolean suspendOrCancel(MemberBean memberBean){
				if(memberBean!=null && memberBean.getMemberID()!=0){
					   if(mdao.suspendOrCancel(memberBean.getMemberAcc())){
						   return true;
					   }	
					}
					return false;
				}
			
	// 依訂單狀態查詢訂單 - Noah
	public List<OrderSumBean> selectOrderSumByOrderCond(Integer memberID,
			Integer orderCondID) {

		return osdao.selectOrderSumByOrderCond(memberID, orderCondID);
	}

	// 查詢訂單狀態之後，可查詢此訂單的訂單明細 - Noah
	public List<OrderDetailBean> queryOrderDetails(Integer orderSumID) {

		return oddao.queryOrderDetails(orderSumID);
	}

	// 依訂單ID查詢訂單 - Noah
	public OrderSumBean queryOneOrderSum(Integer OrderSumID) {

		return osdao.queryOneOrderSum(OrderSumID);
	}

	// 評價訂單 - Noah
	public boolean updateOrderSum(OrderSumBean bean) {

		return osdao.updateOrderSum(bean);

	}

	// 查詢優惠 - Noah
	public MemberBean selectSpecialPrice(Integer memberID) {

		return mdao.selectSpecialPrice(memberID);
	}

	// 獲得優惠 - Noah
	public boolean getSpecialPrice(Integer memberID, Integer specialPriceID) {

		return mdao.getSpecialPrice(memberID, specialPriceID);
	}

	// 使用優惠 - Noah
	public boolean useSpecialPrice(Integer memberID) {

		return mdao.useSpecialPrice(memberID);
	}

	// 查詢所有的優惠券 - Noah
	public List<SpecialPriceBean> queryAllSpecialPrice() {

		List<SpecialPriceBean> lists = spdao.queryAllSpecialPrice();
		List<SpecialPriceBean> newlists = new ArrayList<SpecialPriceBean>();
		Iterator<SpecialPriceBean> iterator = lists.iterator();

		while (iterator.hasNext()) {
			SpecialPriceBean spbean = (SpecialPriceBean) iterator.next();

			if (spbean == null) {
				return null;
			}

			java.sql.Date nowDate = new Date(System.currentTimeMillis());
			java.sql.Date sDate = spbean.getStartDate();
			java.sql.Date eDate = spbean.getEndDate();
			
			if (nowDate.compareTo(sDate) >= 0 && nowDate.compareTo(eDate) <= 0) {
				
				SpecialPriceBean newspbean = new SpecialPriceBean();
				newspbean.setSpecialPriceID(spbean.getSpecialPriceID());
				newspbean.setMealID(spbean.getMealID());
				newspbean.setSpecialPrice(spbean.getSpecialPrice());
				MealBean mbean = new MealBean();
				ShopBean sbean = new ShopBean();
				sbean.setShopName(spbean.getMealBean().getShopBean().getShopName());
				newspbean.setMealBean(mbean);
				newspbean.getMealBean().setShopBean(sbean);
				newspbean.getMealBean().setPrice(spbean.getMealBean().getPrice());
				newspbean.getMealBean().setMealName(spbean.getMealBean().getMealName());
				
				newlists.add(newspbean);
			}
		}

		return newlists;

	}
}
