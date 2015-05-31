package controller;

import model.bean.OwnerBean;
import model.service.OwnerService;

import com.opensymphony.xwork2.ActionSupport;

public class OwnerUpdateAction extends ActionSupport{
	private OwnerService service;
	private OwnerBean bean;

	public void setService(OwnerService service) {
		this.service = service;
	}

	public OwnerBean getBean() {
		return bean;
	}

	public void setBean(OwnerBean bean) {
		this.bean = bean;
	}
	
	public String execute(){
		System.out.println((bean));
		if(service.update(bean)!=null){
			return "success";
		}else{
			return "fail";
		}
		
	}
}
