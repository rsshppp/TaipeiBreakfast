package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import model.bean.MealBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.dao.OrderDetailDAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class OrderDetailDAOHibernate implements OrderDetailDAO {
	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	//查詢訂單明細 - 沛勳 (HQL 改寫為 Criteria - Noah)
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetailBean> queryOrderDetails(Integer orderSumID) {
		Criteria criteria = getSession().createCriteria(OrderDetailBean.class);
		criteria.add(Restrictions.eq("orderSumID", orderSumID));
		List<OrderDetailBean> result=(List<OrderDetailBean>) criteria.list();
		return result;
	}

	
	@Override
	public List<OrderDetailBean> queryOrderDetails(OrderSumBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//宗鈺
        @Override
	public MealBean getMealBean(OrderDetailBean bean) {
		return bean.getMealBean();
	}

	
	//新增訂單明細 - Noah 
	@Override
	public boolean insertOrderDetail(OrderDetailBean bean) {
		if(bean != null){
			this.getSession().save(bean);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean changeOrderDetail(OrderDetailBean bean) {
		
		return false;
	}
	@Override
	public boolean deleteOrderDetail(Integer orderDetailID) {
		
		return false;
	}

	
}
