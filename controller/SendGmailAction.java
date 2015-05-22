package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.service.SendGmailService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import form.SendGmailForm;

public class SendGmailAction extends ActionSupport implements ServletRequestAware{
	HttpSession session=ServletActionContext.getRequest().getSession();
	private String subject;
	private String text;
	private String gmail;
	private SendGmailForm sgf;
	private HttpServletRequest request;
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}

	@Override
	public String execute() throws Exception {
		SendGmailService sgs = new SendGmailService();
		sgs.setGmail(gmail);
		sgs.setSubject(subject);
		sgs.setText(text);
		sgs.SendGmail();
		return "success";
	}
}
