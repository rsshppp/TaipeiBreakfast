package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.bean.MemberBean;
import model.dao.MemberDAO;
import model.misc.convert;
import model.service.TBService;

@WebServlet(urlPatterns = "/pages/MemberInsertServlet.controller")
public class MemberInsertServlet extends HttpServlet {
	
    private TBService tbs;
    private MemberDAO mem;
    @Override
    public void init() throws ServletException {
    	ServletContext application=this.getServletContext();
    	ApplicationContext cont=WebApplicationContextUtils.getWebApplicationContext(application);
    	tbs=(TBService)cont.getBean("sessionFactory");
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String mbmail = request.getParameter("email");
            String mbpwd = request.getParameter("pass01");
            String ckpwd = request.getParameter("pass02");
            
            String mbfirstn = request.getParameter("first");
            String mblastn = request.getParameter("last");

            String mbtel = request.getParameter("tel");
            String mbphone = request.getParameter("pho");
            
            String mbaddr = request.getParameter("addr");
            
//            byte[] mbimg= request.getParameter("img");  用Struts2解決
            
            System.out.println(mbmail+","+mbpwd+","+ckpwd+","+mbfirstn+","+mblastn+","+mbtel+","+mbphone+","+mbaddr);
            
            Map<String, String> errors = new HashMap<String, String>();
            request.setAttribute("erro", errors);
            
			if (mbmail == null) {
				errors.put("acc", "Please enter email");
			}
			boolean ca=tbs.CheackAcc(mbmail);
            if(ca==false){
//            if(!tbs.CheackAcc(mbmail)){
				errors.put("acc", "This email had been used");
            }
            //mail 型態驗證
            if(mbpwd!=ckpwd){
				errors.put("pwd", "password error");
            }

			int tel = 0;
			if (mbtel != null && mbtel.length() != 0) {
				tel = convert.convertInt(mbtel);
				if (tel == -1000) {
					errors.put("tel", "Tel must be integer");
				}
			}
			int pho = 0;
			if (mbphone != null && mbphone.length() != 0) {
				pho = convert.convertInt(mbphone);
				if (pho == -1000) {
					errors.put("pho", "Phone must be integer");
				}
			}
            if (!errors.isEmpty()) {// errors != null &&
                request.getRequestDispatcher("/pages/insertMember1.jsp").forward(request, response);
                return;
            }

			MemberBean bean = new MemberBean();
			bean.setMemberAcc(mbmail);
			bean.setMemberPwd(mbpwd);
			bean.setMemberLastName(mblastn);
			bean.setMemberFirstName(mbfirstn);
			bean.setMemberPhone(mbphone);
			bean.setMemberTel(mbtel);
			bean.setMemberEmail(mbmail);
			bean.setMemberAddr(mbaddr);

//    		bean.setMemberImage(mbimg);
    		
			if(bean!=null){
				System.out.println("inServlet=" + bean);
				MemberBean result = mem.insertMember(bean);
				if (result == null) {
					errors.put("action", "Insert fail");
				} else {
					request.setAttribute("insert", result);
				}
				request.getRequestDispatcher("/pages/insertMember1.jsp").forward(request, response);
				
//            } else {
//                errors.put("action", "Unknown Action");
//                request.getRequestDispatcher("/pages/insertMember1.jsp").forward(request, response);
            }
			
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
	
}
