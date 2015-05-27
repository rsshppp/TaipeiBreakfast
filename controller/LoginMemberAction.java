package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.bean.MemberBean;
import model.service.MemberFunctionService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class LoginMemberAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	private String user;
	private String password;
	private MemberFunctionService memberFunctionService;
	private HttpSession session=ServletActionContext.getRequest().getSession();
	private String suspend;
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSuspend() {
		return suspend;
	}
	public void setSuspend(String suspend) {
		this.suspend = suspend;
	}
	
	public void setMemberFunctionService(MemberFunctionService memberFunctionService) {
		this.memberFunctionService = memberFunctionService;
	}
	
	@Override
	public String execute() throws Exception {
		MemberBean bean=null;
		System.out.println(user);
		System.out.println(password);
		System.out.println(memberFunctionService);
		//user = user.split("@")[0];
		if(user!=null&&user.trim().length()!=0&&password!=null&&password.trim().length()!=0){
			bean=memberFunctionService.login(user, password);
		}
		if(bean!=null&&bean.getMemberSuspend()==false){
			session.setAttribute("user", bean);
			session.setAttribute("type", "member");
			System.out.println("success");
			return "success";
		}else if(bean!=null&&bean.getMemberSuspend()==true){
			suspend="此帳號已被停權";
			System.out.println(suspend);
			return "res";
		}else{
			suspend="錯誤的帳號密碼";
			System.out.println(suspend);
			return "res";
		}
	}
	 public String logout(){
		 session.removeAttribute("user");
		 session.removeAttribute("type");
		 return "success";
	 }
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
}
