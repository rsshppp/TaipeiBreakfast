package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import model.bean.ShopBean;
import model.service.ShopService;

import com.opensymphony.xwork2.ActionSupport;

public class GetShopInfoAction extends ActionSupport implements SessionAware{
	private ShopService service;
	private Integer shopID;
	private Integer ownID;
	private List<ShopBean> list;
	private Map<String, Object> session;
	private String path;
	
//	private ShopBean bean; //for Json 失敗

	public void setService(ShopService service) {
		this.service = service;
	}

	public Integer getShopID() {
		return shopID;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	
	public Integer getOwnID() {
		return ownID;
	}
	
	public void setOwnID(Integer ownID) {
		this.ownID = ownID;
	}
	
//	public ShopBean getBean() {
//		return bean;
//	}
//
//	public void setBean(ShopBean bean) {
//		this.bean = bean;
//	}


	public List<ShopBean> getList() {
		return list;
	}
	
	public void setList(List<ShopBean> list) {
		this.list = list;
	}
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	public String execute(){
		System.out.println(ownID);
		list=new LinkedList();
		ShopBean bean=service.select(shopID);
		System.out.println(bean);
		System.out.println("mark");
		if(bean.getOwnID()!=ownID){  //驗證身分(ownID from session)
			path="forbid.jsp";       //json之struts設置似乎不能轉頁面(除了攔截器的input)
			return "forbid";         //自設驗證身分失敗之path值
		}
		
		if(bean!=null){
			list.add(bean);       //不可直接用list,list為介面
			System.out.println(list);
			session.put("shopBean", bean);
			return "success";
		}else{
			return "fail";
		}
		
	}

}
