package model.dao.imp;

import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.bean.AdministratorBean;
import model.bean.MealBean;
import model.dao.AdministratorDAO;

public class AdministratorDAOHibernate implements AdministratorDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Override
	public AdministratorBean select(String administratorAcc) {
		Criteria criteria=this.getSession().createCriteria(AdministratorBean.class);
		criteria.add(Restrictions.eq("administratorAcc", administratorAcc));
		Iterator<AdministratorBean> list=criteria.list().iterator();
		if(list.hasNext()){
			return list.next();
		}else{
			return null;
		}
	}

}
