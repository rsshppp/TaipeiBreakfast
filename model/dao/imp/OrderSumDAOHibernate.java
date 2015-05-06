package model.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.dao.OrderSumDAO;

public class OrderSumDAOHibernate implements OrderSumDAO {
	
	public OrderSumDAOHibernate() {
		
	}
	
	private SessionFactory sessionFactory;
	public OrderSumDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<OrderSumBean> queryOrderSum(ShopBean bean) {
		
		
		return null;
	}
	//新增訂單 - Noah 幫俊廷 handle
	@Override
	public boolean insertOrderSum(OrderSumBean bean) {
		
		if(bean != null){
			this.getSession().save(bean);
			return true;
		}
		return false;
	}
	//會員進行評價 - Noah
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

	@Override
	public boolean changeOrderCond(Integer orderSumID) {
		
		return false;
	}

	@Override
	public boolean deleteOrderSum(Integer orderSumID) {
		
		return false;
	}
	//會員查詢訂單 - Noah
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> selectAllOrderSum(Integer memberID) {
		
		Query query =
				this.getSession().createQuery("from OrderSumBean where memberID = " + memberID);
		return (List<OrderSumBean>) query.list();
	}
	//會員依訂單狀態查詢訂單 - Noah
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> selectOrderSumByOrderCond(Integer memberID, Integer orderCondID) {
		Query query =
				this.getSession().createQuery("from OrderSumBean where memberID = " + memberID + "and orderCondID = " + orderCondID);
		return (List<OrderSumBean>) query.list();
	}

}
