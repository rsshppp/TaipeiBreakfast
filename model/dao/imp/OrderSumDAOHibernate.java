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
import model.bean.deliverValuesOnly.HistoryRecordBean;
import model.bean.deliverValuesOnly.HistoryOrderDetailBean;
import model.dao.OrderSumDAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> queryOrderSum(ShopBean bean) {
		return (List<OrderSumBean>) this
				.getSession()
				.createQuery(
						"from OrderSumBean as OrderSumBean where OrderSumBean.shopID=:shopID and OrderSumBean.orderCondID<4")
				.setString("shopID", new Integer(bean.getShopID()).toString())
				.list();
	}

	@Override
	public boolean changeOrderCond(Integer orderSumID) {

		return false;
	}

	@Override
	public boolean deleteOrderSum(Integer orderSumID) {
		return false;
	}

	// 新增一筆訂單需要取得店鋪ID、會員ID、總價格、訂單取餐時間 - Noah
	@Override
	public boolean insertOrderSum(OrderSumBean bean) {

		if (bean != null) {
			this.getSession().save(bean);
			return true;
		}
		return false;
	}

	// 進行評價需要取得總訂單ID、會員給予星數、會員給予評價 - Noah
	@Override
	public boolean updateOrderSum(OrderSumBean bean) {

		String hql = "UPDATE OrderSumBean as OrderSumBean SET starsforOwn = :StarsforOwn, evaluateforShop = :evaluateforShop"
				+ " WHERE OrderSumBean.orderSumID = :OrderSumID";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("StarsforOwn", bean.getStarsForOwn());
		query.setParameter("evaluateforShop", bean.getEvaluateForShop());
		query.setParameter("OrderSumID", bean.getOrderSumID());
		int result = query.executeUpdate();
		// System.out.println("Rows affected: " + result);

		if (result != 0) {
			return true;
		}
		return false;
	}

	// 查詢全部屬於某會員的訂單需要會員ID - Noah
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> selectAllOrderSum(Integer memberID) {

		Query query = this.getSession().createQuery(
				"from OrderSumBean where memberID = " + memberID);
		return (List<OrderSumBean>) query.list();
	}

	// 使用訂單狀態查詢屬於某會員的訂單需要會員ID、訂單狀態 - Noah
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> selectOrderSumByOrderCond(Integer memberID,
			Integer orderCondID) {
		Query query = this.getSession().createQuery(
				"from OrderSumBean where memberID = " + memberID
						+ "and orderCondID = " + orderCondID);
		return (List<OrderSumBean>) query.list();
	}
	
	//搜尋最後一筆會員的訂單資訊(老樣子) - Noah
	@SuppressWarnings("unchecked")
	@Override
	public OrderSumBean selectLastOrderSum(Integer memberID) {
		
		Query query = this.getSession().createQuery("from OrderSumBean where memberID = "+ memberID + " and expectTime = (select MAX(expectTime) from OrderSumBean)");
		
		Iterator<OrderSumBean> list = query.list().iterator();

		if (list.hasNext()) {
			return list.next();
		} else {
			return null;
		}
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

	// (-.-)*杜
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> queryOrderSumByTime(int page) {

		Query query = getSession()
				.createQuery("from OrderSumBean order by orderTime desc")
				.setFirstResult(page * 10).setMaxResults(10);
		List<OrderSumBean> result = null;
		if (query != null) {
			result = (List<OrderSumBean>) query.list();
		}
		return result;
	}

	// 宗鈺
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> selectOrderSumByShopID(Integer shopID,
			Integer orderCondID) {
		Criteria criteria = this.getSession()
				.createCriteria(OrderSumBean.class);
		criteria.add(Restrictions.eq("shopID", shopID)).add(
				Restrictions.eq("orderCondID", orderCondID));
		return criteria.list();
	}

	// 宗鈺
	@Override
	public Set<OrderDetailBean> selectOrderDetails(OrderSumBean bean) {
		return bean.getOrderDetail();
	}

	// 宗鈺
	@Override
	public MemberBean getMemberBean(OrderSumBean bean) {
		return bean.getMemberBean();
	}

	// 宗鈺
	@Override
	public ShopBean getShopBean(OrderSumBean bean) {
		return bean.getShopBean();
	}

	// 宗鈺
	@SuppressWarnings("unchecked")
	@Override
	public List<HistoryRecordBean> selectHistoryRecord(Integer shopID,
			Integer orderCondID) {
		List<HistoryRecordBean> list = new ArrayList<HistoryRecordBean>();

		// Query
		// query=this.getSession().createQuery("from OrderSumBean where shopID=:id and orderCondID=:condID");
		// //HQL會失敗原因不知
		// query.setInteger("id", shopID);
		// query.setInteger("condID", orderCondID);
		// Iterator orderSums=query.list().iterator();
		Criteria criteria = this.getSession()
				.createCriteria(OrderSumBean.class);
		criteria.add(Restrictions.eq("shopID", shopID)).add(
				Restrictions.eq("orderCondID", orderCondID));
		Iterator<OrderSumBean> orderSums = criteria.list().iterator();
		while (orderSums.hasNext()) {
			Set<HistoryOrderDetailBean> orderDetailBeans = new HashSet<HistoryOrderDetailBean>();
			HistoryRecordBean historyRecordBean = new HistoryRecordBean();
			OrderSumBean orderSumBean = (OrderSumBean) orderSums.next();
			historyRecordBean.setOrderSumID(orderSumBean.getOrderSumID());
			historyRecordBean.setShopName(orderSumBean.getShopBean()
					.getShopName());
			historyRecordBean.setMemberAcc(orderSumBean.getMemberBean()
					.getMemberAcc());

			historyRecordBean.setTotalPrice(orderSumBean.getTotalPrice());
			historyRecordBean.setOrderTime(orderSumBean.getOrderTime());
			historyRecordBean.setStarsForOwn(orderSumBean.getStarsForOwn());
			historyRecordBean.setEvaluateForShop(orderSumBean
					.getEvaluateForShop());
			Iterator<?> orderDetails = orderSumBean.getOrderDetail().iterator();
			while (orderDetails.hasNext()) {
				OrderDetailBean orderDetailBean = (OrderDetailBean) orderDetails
						.next();
				HistoryOrderDetailBean historyOrderDetailBean = new HistoryOrderDetailBean();
				historyOrderDetailBean.setMealName(orderDetailBean
						.getMealBean().getMealName());
				historyOrderDetailBean.setPrice(orderDetailBean.getPrice());
				historyOrderDetailBean.setCount(orderDetailBean.getCount());
				orderDetailBeans.add(historyOrderDetailBean);
			}
			historyRecordBean.setHistoryOrderDetailBean(orderDetailBeans);
			list.add(historyRecordBean);
		}

		return list;
	}

	@Override
	public boolean insertOrder(OrderSumBean sbean) {

		return false;
	}

}
