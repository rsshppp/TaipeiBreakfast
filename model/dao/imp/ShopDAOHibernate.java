package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.OwnerDAO;
import model.dao.ShopDAO;
import model.misc.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
                                                   //圖片部分未完成!!!
                                                   //配合資料庫TaipeiBreakfast_20150504版本
public class ShopDAOHibernate implements ShopDAO {   
	
	private SessionFactory sessionFactory;
	public ShopDAOHibernate(){
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	//(-.-)*杜
	@Override
	public List<ShopBean> selectAK(String shopArea,String keyword) {
		//shopArea 中符合keyword條件的
		Query query=getSession().createQuery("from ShopBean "
				+ "where shopArea=:ar "
				+ "and shopName like:kw "
				+ "or shopCity like:kw "
				+ "or shopAddr like:kw "
				+ "or businessTimeNote like:kw ");
		query.setString("ar", shopArea);
		query.setString("kw", "%"+keyword+"%");
		List<ShopBean> result=(List<ShopBean>) query.list();
		System.out.println(17+":"+result);
		return result;
	}
	
	//(-.-)*杜
	@Override
	public List<ShopBean> selectKeyword(String keyword) {
		//用 keyword 模糊查詢
		Query query=getSession()
				.createQuery("from ShopBean "
				+ "where shopName like:kw "
				+ "or shopCity like:kw "
				+ "or shopArea like:kw "
				+ "or shopAddr like:kw "
				+ "or businessTimeNote like:kw ")
				.setString("kw", "%"+keyword+"%");
		List<ShopBean> result=(List<ShopBean>) query.list();
		System.out.println(16+":"+result);
		return result;
	}
	
	//(-.-)*杜
	@Override
	public List<ShopBean> selectArea(String shopArea){
		System.out.println(2+":"+shopArea);
		Query query=getSession().createQuery("from ShopBean where shopArea like:status ");
		query.setString("status", shopArea+"%");
		List<ShopBean> result=(List<ShopBean>) query.list();
		System.out.println(15+":"+result);
		return result;
	}
	// result 要有print才能抓到東西,原因不明

	//(-.-)*杜
	@Override
	public List<ShopBean> allowNeedsShop() {
		Query query=getSession().createQuery("from ShopBean where ShopCondID=:status");
		query.setInteger("status", 1);
		List<ShopBean> result=(List<ShopBean>) query.list();
		return result;
	}

	//(-.-)*杜
	@Override
	public boolean allowShop(int ShopID) {
		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Criteria criteria = session.createCriteria(ShopBean.class);
//		criteria.add(Restrictions.eq("shopID", ShopID));
//		Iterator users = criteria.list().iterator();
//		while (users.hasNext()) {
//			ShopBean bean = (ShopBean) users.next();
//			System.out.println(bean);
//			if(bean.getShopCondID()==1){
//				bean.setShopCondID(3);
//				return true;
//			}
//			System.out.println(bean);
//			session.saveOrUpdate(bean);
//		}
//		session.close();

		ShopBean sb = (ShopBean)getSession().get(ShopBean.class, ShopID);
		System.out.println("allow: "+sb);
		if (sb != null) {
			// update ShopCondID
			sb.setShopCondID(3);
			getSession().saveOrUpdate(sb);
			return true;
		}
		return false;
	}

	//(-.-)*杜
	public boolean notAllowShop(int ShopID) {
		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Criteria criteria = session.createCriteria(ShopBean.class);
//		criteria.add(Restrictions.eq("shopID", ShopID));
//		Iterator users = criteria.list().iterator();
//		while (users.hasNext()) {
//			ShopBean bean = (ShopBean) users.next();
//			System.out.println(bean);
//			if(bean.getShopCondID()==1){
//				bean.setShopCondID(2);
//				return true;
//			}
//			System.out.println(bean);
//			session.saveOrUpdate(bean);
//		}
//		session.close();

		ShopBean sb = (ShopBean)getSession().get(ShopBean.class, ShopID);
		System.out.println("not allow: "+sb);
		if (sb != null) {
			// update ShopCondID
			sb.setShopCondID(2);
			getSession().saveOrUpdate(sb);
			return true;
		}
		return false;
	}

	
	//--宗鈺
	public ShopBean select(Integer shopID){
		return (ShopBean)this.getSession().get(ShopBean.class,shopID);
	}
	//--宗鈺
	public ShopBean selectByPhone(String shopPhone){
		Query query=this.getSession().createQuery("from ShopBean where shopPhone=:phone");
		query.setString("phone", shopPhone);
		Iterator list=query.list().iterator();
		if(list.hasNext()){
			ShopBean bean=(ShopBean)list.next();
			return bean;
		}
		return null;
	}
	//--宗鈺
	public List<ShopBean> selectAll(){
		Query query=this.getSession().createQuery("from ShopBean");
		return (List<ShopBean>) query.list();
	}
	
	//--宗鈺
	public List<ShopBean> getShops(Integer ownID){
		Query query=this.getSession().createQuery("from ShopBean where ownID=:ownID");
		query.setInteger("ownID", ownID);
		return (List<ShopBean>) query.list();
	}
	
	//--宗鈺
	public boolean insert(ShopBean shopBean){
		ShopBean bean=null;
		Query query=this.getSession().createQuery("from ShopBean where shopPhone=:phone");
		query.setString("phone", shopBean.getShopPhone());
		Iterator list=query.list().iterator();
		if(list.hasNext()){
			bean=(ShopBean)list.next();
		}
		if(bean==null){
			this.getSession().save(shopBean);
			return true;
		}
		return false;
	}
	
	//--宗鈺
	public ShopBean update(ShopBean shopBean){
		ShopBean bean=(ShopBean)this.getSession().get(ShopBean.class,shopBean.getShopID());
		if(bean!=null){
			bean.setShopName(shopBean.getShopName());
			bean.setShopPhone(shopBean.getShopPhone());
			bean.setShopCity(shopBean.getShopCity());
			bean.setShopArea(shopBean.getShopArea());
			bean.setShopAddr(shopBean.getShopAddr());
			bean.setLastOrderNoon(shopBean.getLastOrderNoon());
			bean.setLastOrderNight(shopBean.getLastOrderNight());
			bean.setOwnID(shopBean.getOwnID());    //此處如何給值待前端做完再想或修改方法
			bean.setLogoImage(shopBean.getLogoImage());  //這裡還未完成!!!!!!!
			bean.setBeginBusinessTime(shopBean.getBeginBusinessTime());
			bean.setEndBusinessTime(shopBean.getEndBusinessTime());
			bean.setBusinessTimeNote(shopBean.getBusinessTimeNote());
			
			bean.setDaysoffBeans(shopBean.getDaysoffBeans());
			bean.setMealBeans(shopBean.getMealBeans());
			return bean;
		}
		
		return null;
	}
	
	//--宗鈺
	public boolean changeShopCondID(Integer newShopCondID,Integer shopID){
		ShopBean bean=(ShopBean)this.getSession().get(ShopBean.class,shopID);
		if(bean!=null){
			bean.setShopCondID(newShopCondID);
			return true;
		}
		 return false;
	}
	
	//--宗鈺
	public boolean suspendOrCancel(Integer shopID){
		ShopBean bean=(ShopBean)this.getSession().get(ShopBean.class,shopID);
		if(bean!=null){
			if(bean.getShopSuspend()==false){
				bean.setShopSuspend(true);
			}else{
				bean.setShopSuspend(false);
			}

			return true;
		}
		return false;
	}
	
	//-chunting
	@Override
	public boolean updateBusinessTime(ShopBean bean) {
		Query query =this.getSession().createQuery(
				"UPDATE ShopBean as ShopBean set beginBusinessTime =:beginBusinessTime, endBusinessTime =:endBusinessTime where ShopBean.shopID=:shopID");
		query.setTime("beginBusinessTime", bean.getBeginBusinessTime());
		query.setTime("endBusinessTime", bean.getEndBusinessTime());
		query.setParameter("shopID", bean.getShopID());
		int i =query.executeUpdate();
		if(i==1){
			return true;
		}
		return false;
	}
	//-chunting
	@Override
	public boolean updateBusinessTimeNote(ShopBean bean) {
		Query query =this.getSession().createQuery("UPDATE ShopBean as ShopBean set businessTimeNote =:businessTimeNote where ShopBean.shopID=:shopID");
		query.setString("businessTimeNote", bean.getBusinessTimeNote());
		query.setParameter("shopID", bean.getShopID());
		int i =query.executeUpdate();
		if(i==1){
			return true;
		}
		return false;
	}
	//-chunting
	@Override
	public List<ShopBean> queryShops(OwnerBean bean) {
		Query query = this.getSession().createQuery("FROM ShopBean as ShopBean WHERE ShopBean.OwnID =:ownID");
		query.setInteger("ownID", bean.getOwnID());
		return query.list();
	}
		
}
