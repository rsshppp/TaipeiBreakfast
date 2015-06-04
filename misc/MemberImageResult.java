package misc;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

import controller.MemberAction;

public class MemberImageResult implements Result{
	private static final long serialVersionUID = 1L;
	public void execute(ActionInvocation invocation) throws Exception {
		 
		MemberAction action = (MemberAction) invocation.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();
 
		response.setContentType(action.getImgType());
		response.getOutputStream().write(action.getImage());
		response.getOutputStream().flush();
 
	}
}
