package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.service.SendGmailService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class SendGmailAction extends ActionSupport implements ServletRequestAware{
	private HttpSession session=ServletActionContext.getRequest().getSession();
	private String subject;
	private String text;
	private String gmail;
	private HttpServletRequest request;
	private SendGmailService sendGmailService;
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public void setSendGmailService(SendGmailService sendGmailService) {
		this.sendGmailService = sendGmailService;
	}

	@Override
	public String execute() throws Exception {
		sendGmailService.setGmail(gmail);
		sendGmailService.setSubject(subject);
		sendGmailService.setText(text);
		sendGmailService.SendGmail();
		return "success";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
}
