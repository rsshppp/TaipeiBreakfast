package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import model.bean.DaysoffBean;
import model.bean.ShopBean;
import model.dao.DaysoffDAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class DaysoffDAOHibernate implements DaysoffDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean insertDaysoff(DaysoffBean bean) {
		
		Criteria criteria = this.getSession().createCriteria(DaysoffBean.class);
		criteria.add(Restrictions.eq("shopID",bean.getShopID()));
		criteria.add(Restrictions.eq("daysoff",bean.getDaysoff()));
		if(!criteria.list().iterator().hasNext()){
			this.getSession().save(bean);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateDaysoff(DaysoffBean bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDaysoff(DaysoffBean bean) {
		Criteria criteria = this.getSession().createCriteria(DaysoffBean.class);
		criteria.add(Restrictions.eq("shopID",bean.getShopID()));
		criteria.add(Restrictions.eq("daysoff",bean.getDaysoff()));
		Iterator iterator = criteria.list().iterator();
		if(iterator.hasNext()){
			DaysoffBean dbean = (DaysoffBean) iterator.next();
			this.getSession().delete(dbean);
			return true;
		}
		return false;
	}

	@Override
	public List<DaysoffBean> queryDaysoff(ShopBean bean) {
		List<DaysoffBean> list = null;
		Query query = this.getSession().createQuery("FROM DaysoffBean WHERE shopID = :shopID");
		list = query.setInteger("shopID", bean.getShopID()).list();
		return list;
	}

}
