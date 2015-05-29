package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.simple.JSONValue;

import model.bean.OwnerBean;
import model.service.OwnerService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;


public class GetOwnerInfoAction extends ActionSupport {   //implements ServletRequestAware不需要
    private OwnerService service;                 //service
  //private HttpServletRequest request;           //request
    
    private Integer ownID;                        //ownAcc,嚴重問題解決
    

	public Integer getOwnID() {
		return ownID;
	}

	public void setOwnID(Integer ownID) {
		this.ownID = ownID;
	}

	private String jsonString;                  //jsonString
	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	
	public void setService(OwnerService service) {
		this.service = service;
	}
    
//	public void setServletRequest(HttpServletRequest request){
//		this.request=request;
//	}
	public String execute(){
//		String ownAcc=request.getParameter("ownAcc");        //沒必要,Struts自動抓值
		System.out.println(ownID);
		OwnerBean bean=service.select(ownID);
		System.out.println(bean);
		
		List  l1 = new LinkedList();
		Map m1 = new HashMap<String, Object>();
		m1.put("ownLastName",bean.getOwnLastName());   
		m1.put("ownFirstName", bean.getOwnFirstName()); 
		m1.put("ownAcc",bean.getOwnAcc()); 
		m1.put("ownEmail",bean.getOwnEmail()); 
		l1.add(m1);
		
//		Gson gson=new Gson();
//		String jsonString = gson.toJson(m1); 
//		System.out.println(m1);
		jsonString=JSONValue.toJSONString(l1);  //別寫 String jsonString, 要加getter and setter
		System.out.println(jsonString);
//		this.setJsonString(jsonString);
		
		return "success";
	} 
}
