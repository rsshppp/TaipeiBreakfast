package model.dao.imp;

import java.util.List;

import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.dao.OrderSumDAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;

public class OrderSumDAOHibernate implements OrderSumDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<OrderSumBean> queryOrderSum(ShopBean bean) {
		List<OrderSumBean> result = null;
		return (List<OrderSumBean>) this
				.getSession()
				.createQuery(
						"from OrderSumBean as OrderSumBean where OrderSumBean.shopID=:shopID")
				.setString("shopID", new Integer(bean.getShopID()).toString())
				.list();
	}

	@Override
	public boolean changeOrderCond(Integer orderSumID) {

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

	@Override
	public boolean updateOrderCond(OrderSumBean bean) {
		// HQL UPDATE
		Query query = this
				.getSession()
				.createQuery(
						"UPDATE OrderSumBean as OrderSumBean set orderCondID = :orderCondID where OrderSumBean.orderSumID=:orderSumID");
		query.setParameter("orderCondID", bean.getOrderCondID());
		query.setParameter("orderSumID", bean.getOrderSumID());

		int i = query.executeUpdate();
		if (i == 1) {
			return true;
		}
		return false;
	}
}
