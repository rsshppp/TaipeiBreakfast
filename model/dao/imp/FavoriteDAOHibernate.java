package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import model.bean.FavoriteBean;
import model.bean.MealBean;
import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.dao.FavoriteDAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class FavoriteDAOHibernate implements FavoriteDAO {
	public FavoriteDAOHibernate() {

	}

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public boolean Create(FavoriteBean bean) {
		if (bean != null) {
			this.getSession().save(bean);
			return true;
		}
		return false;
	}

	@Override
	public List<FavoriteBean> ReadAll(int memberID) {
		Criteria criteria = this.getSession()
				.createCriteria(FavoriteBean.class);
		criteria.add(Restrictions.eq("memberID", memberID));
		return (List<FavoriteBean>) criteria.list();
	}

	@Override
	public boolean Delete(int favoriteID) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(FavoriteBean.class);
		criteria.add(Restrictions.eqOrIsNull("favoriteID", favoriteID));
		Iterator<FavoriteBean> favoriteBeans = criteria.list().iterator();
		if (favoriteBeans != null) {
			while (favoriteBeans.hasNext()) {
				FavoriteBean favoriteBean = (FavoriteBean) favoriteBeans.next();
				session.delete(favoriteBean);
			}
			session.close();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public MealBean getMealBean(FavoriteBean bean) {
		return bean.getMealBean();
	}

	@Override
	public MemberBean getMemberBean(FavoriteBean bean) {
		return bean.getMemberBean();
	}
	
}
