package model.dao.imp;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.bean.OrderSum;
import model.bean.Shop;
import model.dao.OrderSumDAO;

public class OrderSumDAOHibernate implements OrderSumDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<OrderSum> queryOrderSum(Shop bean) {
		List<OrderSum> result = null;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from OrderSum as OrderSum where OrderSum.ShopID=:ShopID");
		query.setString("ShopID", new Integer(bean.getShopID()).toString());
		result = query.list(); 
		return result;
	}

	@Override
	public boolean insertOrderSum(Integer shopID, Integer memberID,
			Double totalPrice, Date expectTime, String memo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrderSum(Integer orderSumID, Integer starsForOwn,
			String evaluateForShop) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeOrderCond(Integer orderSumID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrderSum(Integer orderSumID) {
		// TODO Auto-generated method stub
		return false;
	}

}
