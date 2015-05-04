package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.bean.AdvertisementBean;
import model.bean.AdvertisementStatusBean;
import model.bean.MealKindListBean;
import model.dao.AdvertisementStatusDAO;
//建立-Gary
public class AdvertisementStatusDAOHibernate implements AdvertisementStatusDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	@Override
	public AdvertisementStatusBean selectOne(int asID) {
		AdvertisementStatusBean bean=(AdvertisementStatusBean)this.getSession().get(AdvertisementStatusBean.class, asID);
		return bean;
	}

	@Override
	public List<AdvertisementStatusBean> selectAll() {
		List<AdvertisementStatusBean> list=this.getSession().createQuery("from AdvertisementStatusBean").list();
		return list;
	}

	@Override
	public boolean Insert(AdvertisementStatusBean bean) {
		Criteria criteria=this.getSession().createCriteria(AdvertisementStatusBean.class);
		criteria.add(Restrictions.eq("advertisementStatus", bean.getAdvertisementStatus()));
		Iterator<AdvertisementStatusBean> list=criteria.list().iterator();
		if(list.hasNext()){
			return false;
		}else{
			this.getSession().save(bean);
			return true;
		}
	}

	@Override
	public boolean update(AdvertisementStatusBean bean) {
		AdvertisementStatusBean abean=(AdvertisementStatusBean)this.getSession().get(AdvertisementStatusBean.class, bean.getAdvertisementStatusID());
		if(abean==null){
			return false;
		}else{
			abean.setAdvertisementStatus(bean.getAdvertisementStatus());
			return true;
		}
	}
	
}
