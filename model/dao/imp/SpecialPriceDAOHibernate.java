package model.dao.imp;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.bean.MealBean;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.bean.SpecialPriceBean;
import model.dao.SpecialPriceDAO;

public class SpecialPriceDAOHibernate implements SpecialPriceDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// chunting
	@Override
	public boolean insertSpecialPrice(SpecialPriceBean bean) {
//		this.getSession().save("SpecialPriceBean.class", bean);
		
		Criteria criteria = this.getSession().createCriteria(
				SpecialPriceBean.class);
		criteria.add(Restrictions.eq("mealID", bean.getMealBean().getMealID()));
		criteria.addOrder(Order.desc("specialPriceID"));
		Iterator iterator = criteria.list().iterator();
		if (iterator.hasNext()) {
			SpecialPriceBean sbean = (SpecialPriceBean) iterator.next();
			Long elong = sbean.getEndDate().getTime();
			if (bean.getStartDate().getTime() > elong) {
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	// chunting
	@Override
	public boolean updateSpecialPrice(SpecialPriceBean bean) {

		Criteria criteria = this.getSession().createCriteria(
				SpecialPriceBean.class);
		criteria.add(Restrictions.eq("specialPriceID", bean.getSpecialPriceID()));
		Iterator iterator = criteria.list().iterator();
		if (iterator.hasNext()) {
			SpecialPriceBean sbean = (SpecialPriceBean) iterator.next();
			Long slong = sbean.getStartDate().getTime();
			if (bean.getStartDate().getTime() >= slong) {
				sbean.setEndDate(bean.getEndDate());
				sbean.setStartDate(bean.getStartDate());
				sbean.setMealBean(bean.getMealBean());
				sbean.setSpecialPrice(bean.getSpecialPrice());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteSpecialPrice(SpecialPriceBean bean) {
		Criteria criteria = this.getSession().createCriteria(SpecialPriceBean.class);
		criteria.add(Restrictions.eq("specialPriceID", bean.getSpecialPriceID()));
		Iterator iterator = criteria.list().iterator();
		if(iterator.hasNext()){
			SpecialPriceBean sbean = (SpecialPriceBean) iterator.next();
			this.getSession().delete(sbean);
			return true;
		}
		return false;
	}

	@Override
	public List<SpecialPriceBean> querySpecialPrice(ShopBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	// chunting
	@Override
	public List<SpecialPriceBean> querySpecialPrice(OwnerBean bean) {
		// Query query =
		// this.getSession().createQuery("from SpecialPriceBean as SpecialPriceBean "
		// + "where SpecialPriceBean.mealBean.shopBean.OwnID=:ownID");
		// query.setInteger("ownID", bean.getOwnID());

		Criteria criteria = this.getSession().createCriteria(
				SpecialPriceBean.class);
		criteria.createCriteria("mealBean").createCriteria("shopBean")
				.add(Restrictions.eq("OwnID", bean.getOwnID()));
		return criteria.list();
	}

}
