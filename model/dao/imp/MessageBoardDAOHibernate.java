package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import model.bean.MessageBoardBean;
import model.dao.MessageBoardDAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		return this.getSession().createCriteria(MessageBoardBean.class).list();
		//return this.getSession().createQuery("from MessageBoardBean").list();
	}

	@Override
	public List<MessageBoardBean> select(int memberID) {
		Criteria criteria =this.getSession().createCriteria(MessageBoardBean.class);
		criteria.add(Restrictions.eq("memberID", memberID));
		return criteria.list();
	}

	@Override
	public MessageBoardBean selectOne(int messageID) {
		return (MessageBoardBean)this.getSession().get(MessageBoardBean.class, messageID);
	}

	@Override
	public boolean insert(MessageBoardBean bean) {
		try{
			this.getSession().save(bean);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean delete(int messageID) {
		try{
			this.getSession().delete(this.getSession().get(MessageBoardBean.class, messageID));
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
