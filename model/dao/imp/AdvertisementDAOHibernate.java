package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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
		Criteria criteria =this.getSession().createCriteria(AdvertisementBean.class);
		criteria.add(Restrictions.eq("shopID", shopID));
		List<AdvertisementBean> list=criteria.list();
		return list;
	}
	
	@Override
	public List<AdvertisementBean> selectAll() {
		Criteria criteria =this.getSession().createCriteria(AdvertisementBean.class);
		List<AdvertisementBean> list=criteria.list();
		return null;
	}

	@Override
	public boolean insert(AdvertisementBean bean) {
		this.getSession().save(bean);
		return true;
	}

	@Override
	public boolean updateStatus(int advertisementID, int advertisementStatusID) {
		AdvertisementBean bean=(AdvertisementBean)this.getSession().get(AdvertisementBean.class, advertisementID);
		if(bean!=null){
			bean.setAdvertisementStatusID(advertisementStatusID);
			return true;
		}else{
			return false;
		}
	}

}
