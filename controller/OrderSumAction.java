package controller;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.simple.JSONValue;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Preparable;

import model.bean.MealBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.dao.OrderSumDAO;
import model.dao.imp.OrderSumDAOHibernate;
import model.service.OrderSumService;

public class OrderSumAction {
	/*
	private HttpServletRequest request;
	private ServletContext application;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}

	@Override
	public void prepare() throws Exception {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(application);
		orderSumService = (OrderSumService) context.getBean("orderSumService");
	}
	 */
	private OrderSumService orderSumService;
	private Integer shopID;
	private Integer orderSumID;
	private Integer orderCondID;
	private String jSONString;
	private String updateStatus;
	//用InputStream傳字串到JSP
	private InputStream inputStream;
	
	public void setOrderSumService(OrderSumService orderSumService) {
		this.orderSumService = orderSumService;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}

	public String getjSONString() {
		return jSONString;
	}
	
	public void setOrderSumID(Integer orderSumID) {
		this.orderSumID = orderSumID;
	}

	public void setOrderCondID(Integer orderCondID) {
		this.orderCondID = orderCondID;
	}

	public String queryOrderSum() {
		ShopBean bean = new ShopBean();
		bean.setShopID(shopID);
		List<OrderSumBean> olist = orderSumService.queryOrderSum(bean);

		List<Object> list1 = new ArrayList<Object>();
		// 製作JSON格式
		Iterator oIterator = olist.iterator();
		while (oIterator.hasNext()) {
			List<Object> list2 = new ArrayList<Object>();
			OrderSumBean sbean = (OrderSumBean) oIterator.next();
//			System.out.println("sbean="+sbean);
			if(sbean.getOrderCondID()<3){
				Iterator dIterator = sbean.getOrderDetail().iterator();
				while (dIterator.hasNext()) {
					OrderDetailBean dbean = (OrderDetailBean) dIterator.next();
//				System.out.println("dbean="+dbean);
					// 取mealName, count
					String mealName = dbean.getMealBean().getMealName();
					Integer count = dbean.getCount();
					// 放入Map物件後加入list2
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("mealName", mealName);
					map.put("count", count);
					list2.add(map);
				}
				Double totalPrice = sbean.getTotalPrice();
				Timestamp expectTime = sbean.getExpectTime();
				Integer orderCondID = sbean.getOrderCondID();
				Integer orderSumID = sbean.getOrderSumID();
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("orderSumID", orderSumID);
				map1.put("totalPrice", totalPrice);
				map1.put("expectTime", expectTime.toString().substring(11, 16));
				map1.put("orderCondID", orderCondID);
				map1.put("orderDetail", list2);
				list1.add(map1);
			}
		}
		Map<String, Object> orderSummap = new HashMap<String, Object>();
		orderSummap.put("OrderSums", list1);
		
		this.jSONString = JSONValue.toJSONString(orderSummap);
//		System.out.println(jSONString);
		
//		request.setAttribute("OrderSum", jsonString);
		return "orderSum";
	}
	
	public String updateOrderCond(){
		System.out.println(this.orderSumID+":"+this.orderCondID);
			OrderSumBean orderSumBean = new OrderSumBean();
			orderSumBean.setOrderCondID(orderCondID);
			orderSumBean.setOrderSumID(orderSumID);
			boolean b = orderSumService.updateOrderCond(orderSumBean);
			updateStatus = Boolean.toString(b);
			this.inputStream = new StringBufferInputStream(updateStatus);
//		System.out.println(updateStatus);
		return "updateCond";
	}

}
