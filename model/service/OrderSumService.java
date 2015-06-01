package model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.bean.MealBean;
import model.bean.MealKindListBean;
import model.bean.MemberBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.dao.MealDAO;
import model.dao.MealKindListDAO;
import model.dao.MemberDAO;
import model.dao.OrderSumDAO;

public class OrderSumService {
	private OrderSumDAO orderSumDAO;
	private MealKindListDAO mealKindListDAO;
	private MealDAO mealDAO;
	private MemberDAO memberDAO;
	
	
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	public void setMealDAO(MealDAO mealDAO) {
		this.mealDAO = mealDAO;
	}

	public MealKindListDAO getMealKindListDAO() {
		return mealKindListDAO;
	}

	public void setMealKindListDAO(MealKindListDAO mealKindListDAO) {
		this.mealKindListDAO = mealKindListDAO;
	}

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
	
	//透過店家查詢該店家所有餐點總類
	public List<MealKindListBean> queryMealKindList(ShopBean bean){
		if(bean.getShopID() !=null){
			Set<MealKindListBean> set = mealKindListDAO.queryMealKindList(bean);
			List<MealKindListBean> list = new ArrayList<MealKindListBean>();
			Iterator iterator = set.iterator();
			while(iterator.hasNext()){
				MealKindListBean temp = (MealKindListBean) iterator.next();
				list.add(temp);
			}
			return list;
		}
		return null;
	}
	
	//透過"店家"與"餐點種類"，和分頁查詢該店家的餐點
	public List<MealBean> selectShopMeal(int shopID, int mealKindID){		
		return mealDAO.selectShopMeal(shopID, mealKindID);
	}
	
	//透過餐點ID查詢該餐點圖片
	public byte[] queryMealImage(int mealID){
		return mealDAO.queryMealImage(mealID);
	}
	
	public MemberBean selectMemberByMemberID(int memberID){
		return memberDAO.selectMember(memberID);
	}
}
