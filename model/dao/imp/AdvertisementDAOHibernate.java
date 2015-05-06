package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import model.bean.AdvertisementBean;
import model.bean.AdvertisementStatusBean;
import model.dao.AdvertisementDAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//建立-Gary
public class AdvertisementDAOHibernate implements AdvertisementDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public static void main(String[] args){
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("sessionFactory");
		Session session=sessionFactory.getCurrentSession();
		AdvertisementDAO dao=(AdvertisementDAO)context.getBean("advertisementDAO");
		session.beginTransaction();
		
		Iterator<AdvertisementBean> list=dao.selectAll().iterator();
		while(list.hasNext()){
			System.out.println(list.next());
		}
		
		session.getTransaction().commit();
		context.close();
	}
	@Override
	public AdvertisementBean selectOne(int adID) {
		AdvertisementBean bean=(AdvertisementBean)this.getSession().get(AdvertisementBean.class, adID);
		return bean;
	}

	@Override
	public List<AdvertisementBean> selectShopAd(int shopID) {
		Criteria criteria =this.getSession().createCriteria(AdvertisementBean.class);
		criteria.add(Restrictions.eq("shopID", shopID));
		List<AdvertisementBean> list=criteria.list();
		return list;
	}
	
	@Override
	public List<AdvertisementBean> selectAll() {
		Criteria criteria =this.getSession().createCriteria(AdvertisementBean.class);
		List<AdvertisementBean> list=criteria.list();
		return list;
	}

	@Override
	public boolean insert(AdvertisementBean bean) {
		this.getSession().save(bean);
		return true;
	}

	@Override
	public boolean updateStatus(int advertisementID, AdvertisementStatusBean aStatusBean) {
		AdvertisementBean bean=(AdvertisementBean)this.getSession().get(AdvertisementBean.class, advertisementID);
		if(bean!=null){
			bean.setAdvertisementStatusBean(aStatusBean);
			return true;
		}else{
			return false;
		}
	}

}
