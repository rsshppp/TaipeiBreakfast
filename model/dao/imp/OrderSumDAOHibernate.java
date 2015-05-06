package model.dao.imp;

import java.util.List;

import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.dao.OrderSumDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OrderSumDAOHibernate implements OrderSumDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<OrderSumBean> queryOrderSum(ShopBean bean) {
		List<OrderSumBean> result = null;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from OrderSumBean as OrderSumBean where OrderSumBean.shopID=:shopID");
		query.setString("shopID", new Integer(bean.getShopID()).toString());
		result = query.list(); 
		return result;
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

	@Override
	public boolean insertOrderSum(OrderSumBean bean) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateOrderSum(OrderSumBean bean) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<OrderSumBean> selectAllOrderSum(Integer memberID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<OrderSumBean> selectOrderSumByOrderCond(Integer memberID,
			Integer orderCondID) {
		// TODO Auto-generated method stub
		return null;
	}

}
