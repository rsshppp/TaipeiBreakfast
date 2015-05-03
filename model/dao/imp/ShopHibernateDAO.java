package model.dao.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.ShopDAO;
import model.misc.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
                                                   //圖片部分未完成!!!
                                                   //未整合Spring版本
                                                   //配合資料庫TaipeiBreakfast_20150502版本
public class ShopHibernateDAO implements ShopDAO {                           
	private Session session;
	
	public ShopHibernateDAO(Session session){
		this.session=session;
	}
	
	public ShopBean select(String shopName){
		Query query=session.createQuery("from ShopBean where shopName=:name");
		query.setString("name", shopName);
		Iterator list=query.list().iterator();
		if(list.hasNext()){
			ShopBean bean=(ShopBean)list.next();
			return bean;
		}
		return null;
	}
	
	public List<ShopBean> selectAll(){
		Query query=session.createQuery("from ShopBean");
		return (List<ShopBean>) query.list();
	}
	
	public List<ShopBean> getShops(Integer ownID){
		Query query=session.createQuery("from ShopBean where ownID=:ownID");
		query.setInteger("ownID", ownID);
		return (List<ShopBean>) query.list();
	}
	
	public boolean insert(ShopBean shopBean){
		ShopBean bean=this.select(shopBean.getShopName());
		if(bean==null){
			session.save(shopBean);
			return true;
		}
		return false;
	}
	
	public ShopBean update(ShopBean shopBean){
		ShopBean bean=this.select(shopBean.getShopName());
		if(bean!=null){
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
	
	public boolean changeShopCondID(ShopBean shopBean){
		ShopBean bean=this.select(shopBean.getShopName());
		if(bean!=null){
			bean.setShopCondID(shopBean.getShopCondID());
			return true;
		}
		 return false;
	}
	
	public boolean suspendOrCancel(String shopName){
		ShopBean bean=this.select(shopName);
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
	
	public static void main(String[] args) throws ParseException {      //測試用
		try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			ShopDAO dao=new ShopHibernateDAO(session);
			
//			ShopBean bean=dao.select("Laya");        //select
//			System.out.println(bean);
			
//			List<ShopBean> list=dao.selectAll();     //selectAll
//			System.out.println(list);
			
//			List<ShopBean> list=dao.getShops(4);     //getshops
//			System.out.println(list);
			
//			ShopBean bean=new ShopBean();            //insert
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
//				session=HibernateUtil.getSessionFactory().getCurrentSession();
//				session.beginTransaction();
//				dao=new ShopHibernateDAO(session);
//				ShopBean bean2=dao.select(bean.getShopName());
//				System.out.println(bean2);
//			}else{
//				System.out.println("insert失敗");
//			}
			
//			ShopBean bean=new ShopBean();            //update
//			bean.setShopName("Jackbox");
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
//			bean.setShopName("Jackbox");
//			bean.setShopCondID(2);
//			boolean b=dao.changeShopCondID(bean);
//			System.out.println("店鋪狀態設定是否成功:"+b);
			
//			boolean b=dao.suspendOrCancel("Jackbox");       //為後台管理停權所使用,可以停權,也可以取消停權
//          System.out.println("停權設定是否成功:"+b);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSessionFactory();
		}

	}

}
