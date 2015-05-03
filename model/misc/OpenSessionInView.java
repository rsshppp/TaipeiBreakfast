package model.misc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class OpenSessionInView implements Filter {
	private FilterConfig filterConfig;
	private SessionFactory sessionFactory;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(filterConfig.getServletContext());
		sessionFactory = (SessionFactory) context.getBean(
				filterConfig.getInitParameter("sessionFactoryBeanName"));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			chain.doFilter(request, response);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
