package model.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.bean.AdvertisementBean;
import model.dao.AdvertisementDAO;
//建立-Gary
public class AdvertisementDAOHibernate implements AdvertisementDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	@Override
	public AdvertisementBean selectOne(int adID) {
		AdvertisementBean bean=(AdvertisementBean)this.getSession().get(AdvertisementBean.class, adID);
		return bean;
	}

	@Override
	public List<AdvertisementBean> selectShopAd(int shopID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<AdvertisementBean> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(AdvertisementBean bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStatus(int adID, int asID) {
		// TODO Auto-generated method stub
		return false;
	}

}
