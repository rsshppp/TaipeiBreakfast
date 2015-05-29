package model.dao.imp;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Root;

import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.bean.deliverValuesOnly.HistoryOrderDetailBean;
import model.bean.deliverValuesOnly.HistoryRecordBean;
import model.dao.OrderSumDAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

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

		Criteria criteria = getSession().createCriteria(OrderSumBean.class);
		 
		criteria.add(Restrictions.eq("orderSumID", bean.getOrderSumID()));
		OrderSumBean osbean = (OrderSumBean) criteria.uniqueResult();
		
		getSession().saveOrUpdate(osbean);
			
		return true;
	}

	// 查詢全部屬於某會員的訂單需要會員ID - Noah
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> selectAllOrderSum(Integer memberID) {

		Criteria criteria = getSession().createCriteria(OrderSumBean.class);
		criteria.add(Restrictions.eq("memberID", memberID));
		return (List<OrderSumBean>) criteria.list();
	}

	// 使用訂單狀態查詢屬於某會員的訂單需要會員ID、訂單狀態 - Noah
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderSumBean> selectOrderSumByOrderCond(Integer memberID,
			Integer orderCondID) {
		Criteria criteria = getSession().createCriteria(OrderSumBean.class);
		criteria.add(Restrictions.eq("memberID", memberID));
		criteria.add(Restrictions.eq("orderCondID", orderCondID));
		return (List<OrderSumBean>) criteria.list();
	}
	
	//搜尋最後一筆會員的訂單資訊(老樣子) - Noah
	@SuppressWarnings("unchecked")
	@Override
	public OrderSumBean selectLastOrderSum(Integer memberID) {
		
		Criteria criteria = getSession().createCriteria(OrderSumBean.class);
		
		criteria.add(Restrictions.eq("memberID", memberID));
		
		//子查詢 (expectTime = (select MAX(expectTime) from OrderSumBean))
		DetachedCriteria maxtime = DetachedCriteria.forClass(OrderSumBean.class);
		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.max("expectTime"));
		maxtime.setProjection(proj);
		criteria.add(Subqueries.propertiesEq(new String[]{"expectTime"}, maxtime));
		
		Iterator<OrderSumBean> list = criteria.list().iterator();

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
	@Override
	public List<OrderSumBean> queryOrderSumByTime(int page) {
		Query query = getSession()
				.createQuery("from OrderSumBean order by orderTime desc")
				.setFirstResult(page * 10).setMaxResults(10);
		List<OrderSumBean> result =null;
		result= (List<OrderSumBean>)query.list();
//OrderSumBean.hbm.xml的 ShopBean & MemberBean 關聯必須設定lazy=false,不然list()會死掉(待驗證)
		System.out.println("n06:"+result);
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
     
	//特定店鋪日報表--宗鈺
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDailyReport(Integer shopID, Integer year,  
			Integer month, Integer day) throws ParseException {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate=sdf.parse(year+"-"+month+"-"+day+" 00:00:00");  
		Timestamp startDate1=new Timestamp(startDate.getTime());
		Date endDate=sdf.parse(year+"-"+month+"-"+day+" 23:59:59");
		Timestamp endDate1=new Timestamp(endDate.getTime());
		

		Query query=this.getSession().createQuery("select c.mealName, sum(b.count), avg(b.price), (sum(b.count)*avg(b.price))"
				+ " from OrderSumBean as a JOIN a.OrderDetail as b"  //因只做單項關聯,只能從OrderSumBean                                                     
				+ " JOIN b.mealBean as c"                            //一個帶一個跑	
				+ " where a.expectTime between ? and ?"        //條件由此開始
				+ " and a.shopID=? and a.orderCondID=4"
				+ " group by c.mealName"); 
		
		query.setTimestamp(0, startDate1);
		query.setTimestamp(1, endDate1);
		query.setInteger(2, shopID);
	
		
		return query.list();
	}
	
	//特定店鋪月報表--宗鈺
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMonthlyReport(Integer shopID, Integer year,
			Integer month) throws ParseException {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate=sdf.parse(year+"-"+month+"-"+1+" 00:00:00");  
		Timestamp startDate1=new Timestamp(startDate.getTime());
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);  //month 註標值從0開始
		Integer day=c.getActualMaximum(Calendar.DATE);
//		System.out.println(day);
		Date endDate=sdf.parse(year+"-"+month+"-"+day+" 23:59:59");
		Timestamp endDate1=new Timestamp(endDate.getTime());
		
		Query query=this.getSession().createQuery("select c.mealName, sum(b.count), avg(b.price), (sum(b.count)*avg(b.price))"
				+ " from OrderSumBean as a JOIN a.OrderDetail as b"                                                    
				+ " JOIN b.mealBean as c"                            
				+ " where a.expectTime between ? and ?"        
				+ " and a.shopID=? and a.orderCondID=4"
				+ " group by c.mealName"); 
		
		query.setTimestamp(0, startDate1);
		query.setTimestamp(1, endDate1);
		query.setInteger(2, shopID);
		
		return query.list();
	}
	
	//特定店鋪當日不同時段報表--宗鈺
	@Override
	public List<Object> getTimeReport(Integer shopID, Integer year,
			Integer month, Integer day) throws ParseException {
		List<Object> list=new ArrayList();
		for(int i=5;i<=14;i++){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate=sdf.parse(year+"-"+month+"-"+day+" "+i+":00:00");  //00取出 +" 00:"+i+":00"
			Timestamp startDate1=new Timestamp(startDate.getTime());
//			System.out.println(startDate1);
			Date endDate=sdf.parse(year+"-"+month+"-"+day+" "+i+":59:59");  //i+1改為i+":59:59"
			Timestamp endDate1=new Timestamp(endDate.getTime());
//			System.out.println(endDate1);
			
			Query query=this.getSession().createQuery("select sum(a.totalPrice)"
					+ " from OrderSumBean as a"
					+ " where a.expectTime between ? and ?"        //條件由此開始
					+ " and a.shopID=? and a.orderCondID=4"
                     ); 
			
			query.setTimestamp(0, startDate1);
			query.setTimestamp(1, endDate1);
			query.setInteger(2, shopID);
			
//			System.out.println(query.list());
			if(query.list().toString()=="[]"){
				list.add(0.0);
				continue;
			}
			
			List smallList=query.list();
			Iterator ite=smallList.iterator();
			while(ite.hasNext()){
				Object b=(Object)ite.next();
//				System.out.print(b);
				list.add(b);		
			}
		}
		return list;
	}
	
	//特定店鋪特定時段報表--宗鈺
	@Override
	public List<Object[]> getDetailTimeReport(Integer shopID, Integer year,
			Integer month, Integer day, Integer hour) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate=sdf.parse(year+"-"+month+"-"+day+" "+hour+":00:00");  //00取出 
		Timestamp startDate1=new Timestamp(startDate.getTime());
//		System.out.println(startDate1);
		Date endDate=sdf.parse(year+"-"+month+"-"+day+" "+hour+":59:59");  //i+1改為i+":59:59"
		Timestamp endDate1=new Timestamp(endDate.getTime());
//		System.out.println(endDate1);
		
		
		Query query=this.getSession().createQuery("select c.mealName, sum(b.count), avg(b.price), (sum(b.count)*avg(b.price))"
				+ " from OrderSumBean as a JOIN a.OrderDetail as b"                                                      
				+ " JOIN b.mealBean as c"                            
				+ " where a.expectTime between ? and ?"        
				+ " and a.shopID=? and a.orderCondID=4"
				+ " group by c.mealName"); 
		
		query.setTimestamp(0, startDate1);
		query.setTimestamp(1, endDate1);
		query.setInteger(2, shopID);
//	    System.out.println(query.list());
		
		return query.list();
	}
	
	@Override
	public boolean insertOrder(OrderSumBean sbean) {
		if(!sbean.getOrderDetail().isEmpty()){
			System.out.println("sbean"+sbean);
			this.getSession().save(sbean);
			return true;
		}
		return false;

	}
	
	// 查詢單筆總訂單 - 沛勳 ( HQL 改寫 Criteria - Noah )
	@Override
	public OrderSumBean queryOneOrderSum(Integer OrderSumID) {
		
		Criteria criteria = this.getSession().createCriteria(OrderSumBean.class);
		
		criteria.add(Restrictions.eq("orderSumID", OrderSumID));
		return (OrderSumBean) criteria.list().iterator().next();
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrderSumBean> queryOrderSum(ShopBean bean, Integer page,
			Integer pagesize) {
		return (List<OrderSumBean>) this
				.getSession()
				.createQuery(
						"from OrderSumBean as OrderSumBean where OrderSumBean.shopID=:shopID and OrderSumBean.orderCondID<4")
				.setString("shopID", new Integer(bean.getShopID()).toString())
				.setFirstResult(page*pagesize)
				.setMaxResults(pagesize)
				.list();
	}


}
