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

	public OrderSumDAOHibernate() {
		
	}

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

	//�s�W�q�� - Noah ���T�� handle
	@Override
	public boolean insertOrderSum(OrderSumBean bean) {
		
		if(bean != null){
			this.getSession().save(bean);
			return true;
		}
		return false;
	}

	//�|���i����� - Noah
	@Override
	public boolean updateOrderSum(OrderSumBean bean) {

		String hql = "UPDATE OrderSumBean as OrderSumBean SET starsforOwn = :StarsforOwn, evaluateforShop = :evaluateforShop" + 
	             	 " WHERE OrderSumBean.orderSumID = :OrderSumID";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("StarsforOwn", bean.getStarsForOwn());
		query.setParameter("evaluateforShop", bean.getEvaluateForShop());
		query.setParameter("OrderSumID", bean.getOrderSumID());
		int result = query.executeUpdate();
		//System.out.println("Rows affected: " + result);
	
		if(result != 0){
			return true;
		}
		return false;
	}

	//�|���d�߭q�� - Noah
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> selectAllOrderSum(Integer memberID) {
		
		Query query =
				this.getSession().createQuery("from OrderSumBean where memberID = " + memberID);
		return (List<OrderSumBean>) query.list();
	}

	//�|���̭q�檬�A�d�߭q�� - Noah
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> selectOrderSumByOrderCond(Integer memberID, Integer orderCondID) {
		Query query =
				this.getSession().createQuery("from OrderSumBean where memberID = " + memberID + "and orderCondID = " + orderCondID);
		return (List<OrderSumBean>) query.list();
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
