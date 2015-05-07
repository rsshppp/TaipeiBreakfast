package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.bean.MessageBoardBean;
import model.dao.MessageBoardDAO;

public class MessageBoardDAOHibernate implements MessageBoardDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public static void main(String[] args){
		ConfigurableApplicationContext context;
		Session session;
		MessageBoardDAO dao;

			context=new ClassPathXmlApplicationContext("beans.config.xml");
			SessionFactory sessionFactory=(SessionFactory)context.getBean("sessionFactory");
			session=sessionFactory.getCurrentSession();
			dao=(MessageBoardDAO)context.getBean("messageBoardDAO");
			session.beginTransaction();
			
			Iterator<MessageBoardBean> list=dao.select().iterator();
			while(list.hasNext()){
				System.out.println(list.next());

			session.getTransaction().commit();
			context.close();


		
		
			
		}
	}
	@Override
	public List<MessageBoardBean> select() {
		return this.getSession().createQuery("from MessageBoardBean").list();
	}

	@Override
	public List<MessageBoardBean> select(int memberID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageBoardBean selectOne(int messageID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(MessageBoardBean bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int messageID) {
		// TODO Auto-generated method stub
		return false;
	}

}
