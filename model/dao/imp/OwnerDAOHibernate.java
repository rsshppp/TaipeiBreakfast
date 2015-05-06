package model.dao.imp;

import java.util.Iterator;              
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.OwnerDAO;
import model.misc.HibernateUtil;
                                                       //by宗鈺
                                                       //沒做關連
                                                       //未整合Spring版本
                                                       //配合資料庫TaipeiBreakfast_20150504版本
public class OwnerDAOHibernate implements OwnerDAO {
	
    private Session session;
	
	public OwnerDAOHibernate(Session session){
		this.session=session;
	}
	
	
	public OwnerBean select(String ownerAccount){        //此方法含濾掉status為false之賣家,後台管理用時只需show status為true之賣家
		Query query=session.createQuery("from OwnerBean where ownAcc=:account");
		query.setString("account", ownerAccount);
		Iterator list=query.list().iterator();
		while(list.hasNext()){
			OwnerBean bean=(OwnerBean)list.next();
			if(bean.getOwnStatus()==true){                 //此處過濾
				return bean;
			}		
		}
		return null;
	}
	
//	public OwnerBean select(String ownerAccount){
//		Query query=session.createQuery("from OwnerBean where ownAcc=:account");
//		query.setString("account", ownerAccount);
//		Iterator list=query.list().iterator();
//		if(list.hasNext()){
//			OwnerBean bean=(OwnerBean)list.next();
//			return bean;
//		}
//		return null;
//	}
	
	public List<OwnerBean> selectAll(){
		Query query=session.createQuery("from OwnerBean where ownStatus=:status");
		query.setBoolean("status", true);
		return (List<OwnerBean>) query.list();
	}
	
	
	
	public List<ShopBean> getShops(Integer ownID){
		Query query=session.createQuery("from ShopBean where ownID=:ownID");
		query.setInteger("ownID", ownID);
		return (List<ShopBean>) query.list();
	}
	
	
	public boolean insert(OwnerBean ownerBean){
		OwnerBean bean=this.select(ownerBean.getOwnAcc());
//		System.out.println(bean);
		if(bean==null){
			session.save(ownerBean);
			return true;
		}
		return false;
	}
	
	
	public OwnerBean update(OwnerBean ownerBean){
		OwnerBean bean=this.select(ownerBean.getOwnAcc());
		if(bean!=null){
			bean.setOwnEmail(ownerBean.getOwnEmail());
			bean.setOwnFirstName(bean.getOwnFirstName());
			bean.setOwnLastName(bean.getOwnLastName());
			bean.setOwnPwd(bean.getOwnPwd());
			return bean;
		}
		
		return null;
	}
	
	public OwnerBean updateName(String ownLastName,String ownFirstName,String ownAcc){
		OwnerBean bean=this.select(ownAcc);
		if(bean!=null){
			bean.setOwnLastName(ownLastName);
			bean.setOwnFirstName(ownFirstName);
			return bean;
		}
		return null;
	}
	
	public OwnerBean updateEmail(String ownEmail,String ownAcc){
		OwnerBean bean=this.select(ownAcc);
		if(bean!=null){
			bean.setOwnEmail(ownEmail);
			return bean;
		}
		return null;
	}
	
	public OwnerBean updatePwd(String ownPwd,String ownAcc){
		OwnerBean bean=this.select(ownAcc);
		if(bean!=null){
			bean.setOwnPwd(ownPwd);
			return bean;
		}
		return null;
	}
	
	
	public boolean delete(String ownAcc){
		OwnerBean bean=this.select(ownAcc);
		if(bean!=null){
			bean.setOwnStatus(false);
			return true;
		}
		return false;
	}
	
	public boolean suspendOrCancel(String ownAcc){
		OwnerBean bean=this.select(ownAcc);
		if(bean!=null){
			if(bean.getOwnSuspend()==false){
				bean.setOwnSuspend(true);
			}else{
				bean.setOwnSuspend(false);
			}
			
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {         //測試用
	    try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			OwnerDAO dao=new OwnerDAOHibernate(session);
			
//			OwnerBean bean=dao.select("Laya");    //select
//			System.out.println(bean);
			
//			List<OwnerBean> list=dao.selectAll();    //selectAll
//			System.out.println(list);
			
//			List<ShopBean> list=dao.getShops(4);      //getshops
//			System.out.println(list);
			
//			OwnerBean bean=new OwnerBean();            //insert
//			bean.setOwnAcc("pig");
//			bean.setOwnEmail("pig@gmail.com");
//			bean.setOwnFirstName("Mary");
//			bean.setOwnLastName("BBox");
//			bean.setOwnPwd("456789");
//			//bean.setOwnStatus(true); //已有預設
//			//bean.setOwnSuspend(false);//已有預設
//			if(dao.insert(bean)){
//				session.getTransaction().commit();
//				session=HibernateUtil.getSessionFactory().getCurrentSession();
//				session.beginTransaction();
//				dao=new OwnerDAOHibernate(session);
//				OwnerBean bean2=dao.select(bean.getOwnAcc());
//				System.out.println(bean2);
//			}else{
//				System.out.println("insert失敗");
//			}
			
			
//			OwnerBean bean=new OwnerBean();              //update多項欄位資料,                      
//			bean.setOwnAcc("pig");                       //OwnStatus,OwnSuspend值由其他方法更新
//			bean.setOwnEmail("change2@gmail.com");
//			bean.setOwnFirstName("Mary");
//			bean.setOwnLastName("BBox");
//			bean.setOwnPwd("456789");
//			OwnerBean bean2=dao.update(bean);
//			System.out.println(bean2);
			
//			OwnerBean bean=dao.updateName("王", "大同", "pig");//update Name相關欄位資料
//			System.out.println(bean);
			
			
//			OwnerBean bean=dao.updateEmail("change3@gmail.com", "pig"); //update單一(Email欄位資料)
//			System.out.println(bean);
			
			
//			OwnerBean bean=dao.updatePwd("11111", "pig");    //update單一(密碼欄位資料)
//			System.out.println(bean);	
							
			
//          boolean b=dao.delete("pig");                 //賣方刪帳號時所用的方法 //由session得知owner account
//          System.out.println("delete status:"+b);
			
//          boolean b=dao.suspendOrCancel("pig");       //為後台管理停權所使用,可以停權,也可以取消停權
//          System.out.println("停權設定是否成功:"+b);
			
			session.getTransaction().commit();
		}finally{
			HibernateUtil.closeSessionFactory();
		}
	}

}
