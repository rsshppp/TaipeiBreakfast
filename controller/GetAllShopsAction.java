package controller;

import java.util.LinkedList;
import java.util.List;

import model.bean.ShopBean;
import model.service.ShopService;

import com.opensymphony.xwork2.ActionSupport;

public class GetAllShopsAction extends ActionSupport{
	private ShopService service;
	private List<ShopBean> list;
	public List<ShopBean> getList() {
		return list;
	}
	public void setList(List<ShopBean> list) {
		this.list = list;
	}
	public void setService(ShopService service) {
		this.service = service;
	}
	
	public String execute(){
		list=service.selectAll();
		return "success";
	}
}
