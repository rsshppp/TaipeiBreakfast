package model.dao.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.OwnerDAO;
import model.dao.ShopDAO;
import model.misc.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
                                                   //by宗鈺
                                                   //沒做關連
                                                   //圖片部分未完成!!!
                                                   //配合資料庫TaipeiBreakfast_20150504版本
public class ShopDAOHibernate implements ShopDAO {   
	
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	//(-.-)*杜
	@Override
	public List<ShopBean> selectKeyword(String keyword) {
		//用 keyword 模糊查詢 shopName,shopCity,shopArea
		Query query=getSession().createQuery("from ShopBean where shopName like:st or shopCity like:st or shopArea like:st");
		query.setString("st", "%"+keyword+"%");
		Iterator list=query.list().iterator();
		return (List<ShopBean>) query.list();
	}
	@Override
	public List<ShopBean> selectArea(String shopArea){
		Query query=getSession().createQuery("from ShopBean where shopArea =: status");
		query.setString("status", "%"+shopArea+"%");
		Iterator list=query.list().iterator();
		return (List<ShopBean>) query.list();
	}
	@Override
	public List<ShopBean> allowNeedsShop() {
		Query query=this.getSession().createQuery("from ShopBean where ShopCondID=:status");
		query.setInteger("status", 1);
		Iterator list=query.list().iterator();
		return (List<ShopBean>) query.list();
	}
	@Override
	public boolean allowShop(int ShopID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(ShopBean.class);
		criteria.add(Restrictions.eq("shopID", ShopID));
		Iterator users = criteria.list().iterator();
		while (users.hasNext()) {
			ShopBean bean = (ShopBean) users.next();
			System.out.println(bean);
			if(bean.getShopCondID()==1){
				bean.setShopCondID(2);
				return true;
			}
			System.out.println(bean);
			session.saveOrUpdate(bean);
		}
		tx.commit();
		session.close();
		return false;
	}
	
	public boolean notAllowShop(int ShopID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(ShopBean.class);
		criteria.add(Restrictions.eq("shopID", ShopID));
		Iterator users = criteria.list().iterator();
		while (users.hasNext()) {
			ShopBean bean = (ShopBean) users.next();
			System.out.println(bean);
			if(bean.getShopCondID()==1){
				bean.setShopCondID(3);
				return true;
			}
			System.out.println(bean);
			session.saveOrUpdate(bean);
		}
		tx.commit();
		session.close();
		return false;
	}

	
	//宗鈺
	public ShopBean select(Integer shopID){
		return (ShopBean)this.getSession().get(ShopBean.class,shopID);
	}
	
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
	
	public List<ShopBean> selectAll(){
		Query query=this.getSession().createQuery("from ShopBean");
		return (List<ShopBean>) query.list();
	}
	
	
	public List<ShopBean> getShops(Integer ownID){
		Query query=this.getSession().createQuery("from ShopBean where ownID=:ownID");
		query.setInteger("ownID", ownID);
		return (List<ShopBean>) query.list();
	}
	
	
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
			//bean.setLogoImage(shopBean.getLogoImage());  //這裡還未完成!!!!!!!
			bean.setBeginBusinessTime(shopBean.getBeginBusinessTime());
			bean.setEndBusinessTime(shopBean.getEndBusinessTime());
			bean.setBusinessTimeNote(shopBean.getBusinessTimeNote());
			return bean;
		}
		
		return null;
	}
	
	
	public boolean changeShopCondID(Integer shopCondID,Integer shopID){
		ShopBean bean=(ShopBean)this.getSession().get(ShopBean.class,shopID);
		if(bean!=null){
			bean.setShopCondID(shopCondID);
			return true;
		}
		 return false;
	}
	
	
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
	
	public static void main(String[] args) throws ParseException {      //測試用
		
		   ApplicationContext context=new ClassPathXmlApplicationContext("beans.config.xml");
		   SessionFactory sessionFactory=(SessionFactory)context.getBean("sessionFactory");
		   Session session=sessionFactory.getCurrentSession();
		   session.beginTransaction();
		
		   ShopDAO dao=(ShopDAOHibernate)context.getBean("shopDAOHibernate");
			                    
			
//			ShopBean bean=dao.select(3);                       //select
//			System.out.println(bean); //假設session裡的shopID為3
	
			
//			List<ShopBean> list=dao.selectAll();              //selectAll
//			System.out.println(list);
	
			
//			List<ShopBean> list=dao.getShops(4);              //getshops
//			System.out.println(list);  //假設session裡的ownID為4
	
			
//			ShopBean bean=new ShopBean();                     //insert
//			bean.setShopName("Jackbox");
//			bean.setShopPhone("02-87654321");
//			bean.setShopCity("台北");
//			bean.setShopArea("中山區");
//			bean.setShopAddr("濱江路");
//			SimpleDateFormat sdf = new SimpleDateFormat("k:mm");
//			java.sql.Time time1=new java.sql.Time(sdf.parse("12:30").getTime());
//			bean.setLastOrderNoon(time1);
//			java.sql.Time time2=new java.sql.Time(sdf.parse("00:30").getTime());
//			bean.setLastOrderNight(time2);
//			bean.setOwnID(4);    //此處如何給值待前端做完再想或修改方法
//			//bean.setLogoImage(logoImage);   //這裡還未完成!!!!!!!
//			//bean.setShopSuspend(shopSuspend);//已有預設
//			//bean.setShopCondID(1); //已有預設
//			java.sql.Time time3=new java.sql.Time(sdf.parse("8:30").getTime());
//			bean.setBeginBusinessTime(time3);
//			java.sql.Time time4=new java.sql.Time(sdf.parse("12:30").getTime());
//			bean.setEndBusinessTime(time4);
//			bean.setBusinessTimeNote("星期二公休");
//			if(dao.insert(bean)){
//				session.getTransaction().commit();
//				session=sessionFactory.getCurrentSession();
//				session.beginTransaction();
//				dao=(ShopDAOHibernate)context.getBean("shopDAOHibernate");
//				ShopBean bean2=dao.selectByPhone(bean.getShopPhone());
//				System.out.println(bean2);
//			}else{
//				System.out.println("insert失敗");
//			}
		
			
//			ShopBean bean=new ShopBean();            //update
//			bean.setShopID(12);  //假設session裡的shopID為12
//			bean.setShopName("dogfood");
//			bean.setShopPhone("02-77777777");
//			bean.setShopCity("台北");
//			bean.setShopArea("中山區");
//			bean.setShopAddr("公園路123號");
//			SimpleDateFormat sdf = new SimpleDateFormat("k:mm");
//			java.sql.Time time1=new java.sql.Time(sdf.parse("11:30").getTime());
//			bean.setLastOrderNoon(time1);
//			java.sql.Time time2=new java.sql.Time(sdf.parse("23:30").getTime());
//			bean.setLastOrderNight(time2);
//			bean.setOwnID(4);    //此處如何給值待前端做完再想或修改方法
//			//bean.setLogoImage(logoImage);   //這裡還未完成!!!!!!!
//			java.sql.Time time3=new java.sql.Time(sdf.parse("7:30").getTime());
//			bean.setBeginBusinessTime(time3);
//			java.sql.Time time4=new java.sql.Time(sdf.parse("13:30").getTime());
//			bean.setEndBusinessTime(time4);
//			bean.setBusinessTimeNote("星期四公休");
//			ShopBean bean2=dao.update(bean);
//			System.out.println(bean2);
		
			
//			ShopBean bean=new ShopBean();                  //更改店鋪狀態ID
//			bean.setShopID(12);//假設session裡的shopID為12
//			boolean b=dao.changeShopCondID(2,bean.getShopID());
//			System.out.println("店鋪狀態設定是否成功:"+b);
		
			
//			boolean b=dao.suspendOrCancel(12);       //為後台管理停權所使用,可以停權,也可以取消停權    //假設session裡的shopID為12
//          System.out.println("停權設定是否成功:"+b);
			
		    session.getTransaction().commit();
		    sessionFactory.close();
		    ((ConfigurableApplicationContext)context).close();

	}


	

}
