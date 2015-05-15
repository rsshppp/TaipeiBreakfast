package misc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet Filter implementation class OpenSessionInViewFilter
 */
//@WebFilter("/OpenSessionInViewFilter")
public class OpenSessionInViewFilter implements Filter {
	private SessionFactory sessionFactory=null;
	//SessionFactory
	public void init(FilterConfig Config) throws ServletException {	
		String sessionFactoryBeanName=Config.getInitParameter("sessionFactoryBeanName");
		ServletContext application=Config.getServletContext();
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(application);
		sessionFactory=(SessionFactory)context.getBean(sessionFactoryBeanName);
	}
	
	//Transaction
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			System.out.println("openview");
			sessionFactory.getCurrentSession().beginTransaction();
			chain.doFilter(request, response);
			sessionFactory.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			sessionFactory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
			chain.doFilter(request, response);
		}
	}
	
    public OpenSessionInViewFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}
	
}
