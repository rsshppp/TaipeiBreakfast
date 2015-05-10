package model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.deliverValuesOnly.HistoryOrderDetailBean;
import model.bean.deliverValuesOnly.HistoryRecordBean;
import model.dao.OrderDetailDAO;
import model.dao.OrderSumDAO;

public class TransactionHistoryRecordService {            //by宗鈺-查詢交易歷史記錄
	private OrderSumDAO orderSumDAO;
	private OrderDetailDAO orderDetailDAO;
    	
	
	public void setOrderSumDAO(OrderSumDAO orderSumDAO) {
		this.orderSumDAO = orderSumDAO;
	}
	public void setOrderDetailDAO(OrderDetailDAO orderDetailDAO) {
		this.orderDetailDAO = orderDetailDAO;
	}
	
	public List<HistoryRecordBean> selectHistoryRecord1(Integer shopID, Integer orderCondID) {
		if(shopID!=0 && orderCondID!=0){
			return orderSumDAO.selectHistoryRecord(shopID, orderCondID);
		}
		return null;
	}
	
	public List<HistoryRecordBean> selectHistoryRecord(Integer shopID, Integer orderCondID) {
		List<HistoryRecordBean> list=new ArrayList();  //important to write here
		
		List<OrderSumBean> listOrderSumBean=orderSumDAO.selectOrderSumByShopID(shopID, orderCondID);
		Iterator orderSums=listOrderSumBean.iterator();
		//System.out.println(listOrderSumBean);
		while(orderSums.hasNext()){
			Set<HistoryOrderDetailBean> orderDetailBeans=new HashSet<HistoryOrderDetailBean>(); //important to write here
			HistoryRecordBean historyRecordBean=new HistoryRecordBean();
			OrderSumBean orderSumBean=(OrderSumBean)orderSums.next();
			historyRecordBean.setOrderSumID(orderSumBean.getOrderSumID());
			                             //資料庫存取交由DAO來做
			historyRecordBean.setShopName(orderSumDAO.getShopBean(orderSumBean).getShopName());
			historyRecordBean.setMemberAcc(orderSumDAO.getMemberBean(orderSumBean).getMemberAcc());
			
			historyRecordBean.setTotalPrice(orderSumBean.getTotalPrice());
			historyRecordBean.setOrderTime(orderSumBean.getOrderTime());
			historyRecordBean.setStarsForOwn(orderSumBean.getStarsForOwn());
			historyRecordBean.setEvaluateForShop(orderSumBean.getEvaluateForShop());
			Iterator orderDetails=orderSumDAO.selectOrderDetails(orderSumBean).iterator();
			//System.out.println(orderSumDAO.selectOrderDetails(orderSumBean));
			while(orderDetails.hasNext()){
				OrderDetailBean orderDetailBean=(OrderDetailBean)orderDetails.next();
				//System.out.println(orderDetailBean);
				HistoryOrderDetailBean historyOrderDetailBean=new HistoryOrderDetailBean();
				
				historyOrderDetailBean.setMealName(orderDetailDAO.getMealBean(orderDetailBean).getMealName());
				
				historyOrderDetailBean.setPrice(orderDetailBean.getPrice());
				historyOrderDetailBean.setCount(orderDetailBean.getCount());
				orderDetailBeans.add(historyOrderDetailBean);
			}
			historyRecordBean.setHistoryOrderDetailBean(orderDetailBeans);
			list.add(historyRecordBean);
		}
        return list;
	}

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("sessionFactory");
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		
		TransactionHistoryRecordService service=(TransactionHistoryRecordService)context
				.getBean("transactionHistoryRecordService");
//		List<HistoryRecordBean> list=service.selectHistoryRecord(3, 4);
		List<HistoryRecordBean> list=service.selectHistoryRecord1(3, 4);
		System.out.println(list);
		
		
		
		
		session.getTransaction().commit();
		sessionFactory.close();
		((ConfigurableApplicationContext)context).close();

	}

}
