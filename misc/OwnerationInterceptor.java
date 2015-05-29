package misc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.bean.OwnerBean;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class OwnerationInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx=invocation.getInvocationContext();
		Map<String, Object> session=ctx.getSession();
		Object object=session.get("user");
		String type=(String)session.get("type");
		System.out.println("驗證中");
		if(object!=null&&type!=null){
			if(object instanceof OwnerBean){
				//System.out.println("oenerbean");
				return invocation.invoke();
			}else{
				//System.out.println("user=?");
				return "login";
			}
		}else{
			HttpServletRequest request = ServletActionContext.getRequest();
			String location = request.getRequestURI();
			session.put("location", location);
			//System.out.println("沒登入");
			return "login";
		}
	}

}
