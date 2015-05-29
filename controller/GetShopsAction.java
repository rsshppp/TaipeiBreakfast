package controller;

import java.util.List;

import model.bean.ShopBean;
import model.service.OwnerService;

import com.opensymphony.xwork2.ActionSupport;

public class GetShopsAction extends ActionSupport{
	private OwnerService service;
	private List<ShopBean> list;
	
	private Integer ownID;

	public Integer getOwnID() {
		return ownID;
	}

	public void setOwnID(Integer ownID) {
		this.ownID = ownID;
	}

	public void setService(OwnerService service) {
		this.service = service;
	} 
	
	
	public List<ShopBean> getList() {
		return list;
	}

	public void setList(List<ShopBean> list) {
		this.list = list;
	}

	public String execute(){
		if(service.getShops(ownID)!=null){
			list=service.getShops(ownID);
			return "success";
		}else{
			return "fail";
		}
		
	}
}
