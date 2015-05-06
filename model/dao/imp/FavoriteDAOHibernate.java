package model.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.bean.AdvertisementBean;
import model.bean.FavoriteBean;
import model.bean.OwnerBean;
import model.dao.FavoriteDAO;

public class FavoriteDAOHibernate implements FavoriteDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public boolean Create(FavoriteBean bean) {
		this.getSession().save(bean);
		return true;
	}

	@Override
	public List<FavoriteBean> ReadAll(int memberID) {
		Criteria criteria =this.getSession().createCriteria(FavoriteBean.class);
		criteria.add(Restrictions.eq("memberID", memberID));
		List<FavoriteBean> list=criteria.list();
		return list;
	}

	@Override
	public boolean Delete(int favoriteID) {
		return false;
	}

}
