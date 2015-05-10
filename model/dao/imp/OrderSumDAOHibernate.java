package model.dao.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.bean.deliverValuesOnly.HistoryOrderDetailBean;
import model.bean.deliverValuesOnly.HistoryRecordBean;
import model.dao.OrderSumDAO;
import model.misc.HibernateUtil;
import model.dao.imp.OrderSumDAOHibernate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.StaleStateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		return (List<OrderSumBean>) this.getSession().createQuery("from OrderSumBean as OrderSumBean where OrderSumBean.shopID=:shopID")
				.setString("shopID", new Integer(bean.getShopID()).toString()).list();
	}

	@Override
	public boolean changeOrderCond(Integer orderSumID) {

		return false;
	}

	@Override
	public boolean deleteOrderSum(Integer orderSumID) {
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
	
	//(-.-)*杜
	@Override
	public List<OrderSumBean> queryOrderSumByTime(int page) {
			
		Query query =getSession().createQuery("from OrderSumBean order by orderTime desc")
								.setFirstResult(page * 10).setMaxResults(10);
		List<OrderSumBean> result=null;
		if(query!=null){
		         result = (List<OrderSumBean>) query.list();
		}
		   return result;
		}
	
	//宗鈺
	@Override
	public List<OrderSumBean> selectOrderSumByShopID(Integer shopID,Integer orderCondID) {
		Criteria criteria=this.getSession().createCriteria(OrderSumBean.class);
		criteria.add(Restrictions.eq("shopID", shopID)).add(Restrictions.eq("orderCondID", orderCondID));
		return criteria.list();
	}
	
	//宗鈺
	@Override
	public Set<OrderDetailBean> selectOrderDetails(OrderSumBean bean) {
			return bean.getOrderDetail();
	}
	
	//宗鈺
	@Override
	public MemberBean getMemberBean(OrderSumBean bean) {
		return bean.getMemberBean();
	}
	
	//宗鈺
	@Override
	public ShopBean getShopBean(OrderSumBean bean) {
		return bean.getShopBean();
	}
	
	//宗鈺
	@Override
	public List<HistoryRecordBean> selectHistoryRecord(Integer shopID, Integer orderCondID) {
		Set<HistoryOrderDetailBean> orderDetailBeans=null;
		List<HistoryRecordBean> list=new ArrayList();
//		Query query=this.getSession().createQuery("from OrderSumBean where shopID=:id and orderCondID=:condID");
//		query.setInteger("id", shopID);
//		query.setInteger("condID", orderCondID);
//		Iterator orderSums=query.list().iterator();
		Criteria criteria=this.getSession().createCriteria(OrderSumBean.class);
		criteria.add(Restrictions.eq("shopID", shopID)).add(Restrictions.eq("orderCondID", orderCondID));
		Iterator orderSums=criteria.list().iterator();
		while(orderSums.hasNext()){
			HistoryRecordBean historyRecordBean=new HistoryRecordBean();
			OrderSumBean orderSumBean=(OrderSumBean)orderSums.next();
			historyRecordBean.setOrderSumID(orderSumBean.getOrderSumID());
			historyRecordBean.setShopName(orderSumBean.getShopBean().getShopName());
			historyRecordBean.setMemberAcc(orderSumBean.getMemberBean().getMemberAcc());
				
			historyRecordBean.setTotalPrice(orderSumBean.getTotalPrice());
			historyRecordBean.setOrderTime(orderSumBean.getOrderTime());
			historyRecordBean.setStarsForOwn(orderSumBean.getStarsForOwn());
			historyRecordBean.setEvaluateForShop(orderSumBean.getEvaluateForShop());
			Iterator orderDetails= orderSumBean.getOrderDetail().iterator();
			while(orderDetails.hasNext()){
				orderDetailBeans=new HashSet<HistoryOrderDetailBean>();
				OrderDetailBean orderDetailBean=(OrderDetailBean)orderDetails.next();
				HistoryOrderDetailBean historyOrderDetailBean=new HistoryOrderDetailBean();
				historyOrderDetailBean.setMealName(orderDetailBean.getMealBean().getMealName());
				historyOrderDetailBean.setPrice(orderDetailBean.getPrice());
				historyOrderDetailBean.setCount(orderDetailBean.getCount());
				orderDetailBeans.add(historyOrderDetailBean);
			}
			historyRecordBean.setHistoryOrderDetailBean(orderDetailBeans);
			list.add(historyRecordBean);
		}
			
		return list;
	}
		
}
