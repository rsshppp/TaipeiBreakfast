package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.dao.OrderDetailDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

	@Override
	public List<OrderDetailBean> queryOrderDetails(Integer orderSumID) {
		Query query =getSession().createQuery("from OrderDetail where orderSumID =: st");
		query.setString("st", "%"+orderSumID+"%");
		Iterator list=query.list().iterator();
		return (List<OrderDetailBean>) query.list();
	}
	@Override
	public List<OrderDetailBean> queryOrderDetails(OrderSumBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
