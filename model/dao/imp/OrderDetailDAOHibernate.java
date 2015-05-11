package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import model.bean.MealBean;
import model.bean.OrderDetailBean;
import model.dao.OrderDetailDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OrderDetailDAOHibernate implements OrderDetailDAO {
	
	public OrderDetailDAOHibernate() {
		
	}
	
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

	@Override
	public List<OrderDetailBean> queryOrderDetails(Integer orderSumID) {
		Query query =getSession().createQuery("from OrderDetail where orderSumID =: st");
		query.setString("st", "%"+orderSumID+"%");
		Iterator list=query.list().iterator();
		return (List<OrderDetailBean>) query.list();
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
