package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import model.bean.ShopBean;
import model.service.ShopService;

import com.opensymphony.xwork2.ActionSupport;

public class GetShopOneAction extends ActionSupport implements  SessionAware{ //ServletRequestAware,
	private ShopService service;
//	private HttpServletRequest request; 
	private Map<String, Object> session;
	private Integer shopID;

	public void setService(ShopService service) {
		this.service = service;
	}

//	@Override
//	public void setServletRequest(HttpServletRequest request) {
//		this.request = request;		
//	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	public Integer getShopID() {
		return shopID;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}

	public String execute(){
//		System.out.println(request.getParameter("shopID"));
//		Integer shopID=Integer.parseInt(request.getParameter("shopID"));
		ShopBean bean=service.select(shopID);
		session.put("shopBean", bean);
		return "success";
		
	}

	

	
}
