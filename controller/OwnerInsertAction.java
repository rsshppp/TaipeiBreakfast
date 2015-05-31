package controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import model.bean.OwnerBean;
import model.service.OwnerService;

import com.opensymphony.xwork2.ActionSupport;

public class OwnerInsertAction extends ActionSupport implements SessionAware{
	private OwnerBean bean;
    private OwnerService service;
    
    private Map<String, Object> session;
	
	public OwnerBean getBean() {
		return bean;
	}

	public void setBean(OwnerBean bean) {
		this.bean = bean;
	}

	public void setService(OwnerService service) {
		this.service = service;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	public String execute(){
		System.out.println(bean);
		bean.setOwnAcc(bean.getOwnEmail());
		service.insert(bean);
		session.put("ownerAcc", bean.getOwnAcc());
		session.put("ownerID", bean.getOwnID());
		return "success";
	}


}
