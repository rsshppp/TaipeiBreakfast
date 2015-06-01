package model.dao.imp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.bean.MealBean;
import model.bean.MealKindListBean;
import model.dao.MealKindListDAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.bean.ShopBean;

//建立-Gary
public class MealKindListDAOHibernate implements MealKindListDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public MealKindListBean selectOne(int mklID) {
		MealKindListBean bean=(MealKindListBean)this.getSession().get(MealKindListBean.class, mklID);
		return bean;
	}

	@Override
	public List<MealKindListBean> selectAll() {
		List<MealKindListBean> list=this.getSession().createQuery("from MealKindListBean").list();
		return list;
	}

	@Override
	public boolean insert(MealKindListBean bean) {
		Criteria criteria=this.getSession().createCriteria(MealKindListBean.class);
		criteria.add(Restrictions.eq("mealKindName", bean.getMealKindName()));
		Iterator<MealKindListBean> iterator=criteria.list().iterator();
		if(iterator.hasNext()){
			return false;
		}else{
			this.getSession().save(bean);
			return true;
		}
	}

	@Override
	public boolean update(MealKindListBean bean) {
		MealKindListBean result=(MealKindListBean)this.getSession().get(MealKindListBean.class, bean.getMealKindID());
		if(result==null){
			return false;
		}else{
			result.setMealKindName(bean.getMealKindName());
			result.setDefaultImage(bean.getDefaultImage());
			return true;
		}
	}

	@Override
	public Set<MealKindListBean> queryMealKindList(ShopBean bean) {
		Set<MealKindListBean> results = new HashSet<MealKindListBean>();
		Criteria criteria=this.getSession().createCriteria(MealBean.class);
		criteria.add(Restrictions.eq("shopID", bean.getShopID()));
		List<MealBean> list=criteria.list();
		Iterator i = list.iterator();
		while(i.hasNext()){
			MealBean mbean = (MealBean) i.next();
			MealKindListBean mklbean = mbean.getMealKindListBean();
			results.add(mklbean);
		}
		return results;
	}

}
