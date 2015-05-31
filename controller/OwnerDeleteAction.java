package controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import model.service.OwnerService;

import com.opensymphony.xwork2.ActionSupport;

public class OwnerDeleteAction extends ActionSupport implements ServletRequestAware{
	private OwnerService service;
	private HttpServletRequest request;
	
	
	public void setService(OwnerService service) {
		this.service = service;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String execute(){
		String ownID=request.getParameter("ownID");
		if(service.delete(Integer.parseInt(ownID))){
			return "success";
		}
		return "fail";
	}
}
