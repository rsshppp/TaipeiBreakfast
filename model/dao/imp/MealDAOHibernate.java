package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.bean.MealBean;
import model.bean.MealKindListBean;
import model.bean.OrderDetailBean;
import model.dao.MealDAO;
//建立-Gary
public class MealDAOHibernate implements MealDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public MealBean queryMealBean(OrderDetailBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MealBean> selectShopMeal(int shopID, int mealKindID) {
		Criteria criteria=this.getSession().createCriteria(MealBean.class);
		criteria.add(Restrictions.eq("shopID", shopID));
		criteria.add(Restrictions.eq("mealKindID", mealKindID));
		List<MealBean> list=criteria.list();
		return list;
	}

	@Override
	public MealBean selectOneMeal(String mName, int shopID) {
		Criteria criteria=this.getSession().createCriteria(MealBean.class);
		criteria.add(Restrictions.eq("mealName", mName));
		criteria.add(Restrictions.eq("shopID", shopID));
		Iterator<MealBean> list=criteria.list().iterator();
		if(list.hasNext()){
			return list.next();
		}else{
			return null;
		}
	}

	@Override
	public MealBean selectOneMeal(int mealID) {
		MealBean bean=(MealBean)this.getSession().get(MealBean.class, mealID);
		return bean;
	}

	@Override
	public List<MealBean> selectShopMeal(int shopID) {
		Criteria criteria=this.getSession().createCriteria(MealBean.class);
		criteria.add(Restrictions.eq("shopID", shopID));
		List<MealBean> list=criteria.list();
		return list;
	}

	@Override
	public boolean insert(MealBean bean) {
		Criteria criteria=this.getSession().createCriteria(MealBean.class);
		criteria.add(Restrictions.eq("shopID", bean.getShopID()));
		criteria.add(Restrictions.eq("mealName", bean.getMealName()));
		Iterator<MealBean> list=criteria.list().iterator();
		if(list.hasNext()){
			return false;			
		}else{
			this.getSession().save(bean);
			return true;
		}
	}

	@Override
	public boolean update(MealBean bean) {
		MealBean result=(MealBean)this.getSession().get(MealBean.class, bean.getMealID());
		System.out.println("MBH:"+result);
		if(result==null){
			return false;
		}else{
			result.setMealName(bean.getMealName());
			result.setPrice(bean.getPrice());
			//result.setMealKindID(bean.getMealKindID());
			result.setMealKindListBean(bean.getMealKindListBean());
			result.setMealImage(bean.getMealImage());
			
			return true;
		}
	}

	@Override
	public boolean updateMealStatus(int mealID,boolean mealStatus) {
		MealBean result=(MealBean)this.getSession().get(MealBean.class, mealID);
		if(result==null){
			return false;
		}else{
			result.setMealStatus(mealStatus);
			return true;
		}
	}

}
