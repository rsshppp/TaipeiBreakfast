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
                                                       //未整合Spring版本
                                                       //配合TaipeiBreakfast_20150502版本
public class OwnerHibernateDAO implements OwnerDAO {
	
    private Session session;
	
	public OwnerHibernateDAO(Session session){
		this.session=session;
	}
	
	
	public OwnerBean select(String ownerAccount){
		Query query=session.createQuery("from OwnerBean where ownAcc=:account");
		query.setString("account", ownerAccount);
		Iterator list=query.list().iterator();
		if(list.hasNext()){
			OwnerBean bean=(OwnerBean)list.next();
			return bean;
		}
		return null;
	}
	
	
	public List<OwnerBean> selectAll(){
		Query query=session.createQuery("from OwnerBean");
		return (List<OwnerBean>) query.list();
	}
	
	
	
	public List<ShopBean> getShops(Integer ownID){
		Query query=session.createQuery("from ShopBean where ownID=:ownID");
		query.setInteger("ownID", ownID);
		return (List<ShopBean>) query.list();
	}
	
	
	public OwnerBean insert(OwnerBean ownerBean){
		OwnerBean bean=this.select(ownerBean.getOwnAcc());
		if(bean==null){
			session.save(ownerBean);
			return ownerBean;
		}
		return null;
	}
	
	
	public OwnerBean update(OwnerBean ownerBean){
		OwnerBean bean=this.select(ownerBean.getOwnAcc());
		if(bean!=null){
			bean.setOwnAcc(ownerBean.getOwnAcc());
			bean.setOwnEmail(ownerBean.getOwnEmail());
			bean.setOwnFirstName(bean.getOwnFirstName());
			bean.setOwnLastName(bean.getOwnLastName());
			bean.setOwnPwd(bean.getOwnPwd());
			bean.setOwnStatus(bean.getOwnStatus());
			bean.setOwnSuspend(bean.getOwnSuspend());
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
	
	
	
	public static void main(String[] args) {         //測試用
	    try {
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			OwnerDAO dao=new OwnerHibernateDAO(session);
			
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
//			bean.setOwnStatus(true);
//			bean.setOwnSuspend(false);
//			OwnerBean bean2=dao.insert(bean);
//			System.out.println(bean2);
			
//			OwnerBean bean=new OwnerBean();              //update多項欄位資料
//			bean.setOwnID(7);
//			bean.setOwnAcc("pig");
//			bean.setOwnEmail("change2@gmail.com");
//			bean.setOwnFirstName("Mary");
//			bean.setOwnLastName("BBox");
//			bean.setOwnPwd("456789");
//			bean.setOwnStatus(true);
//			bean.setOwnSuspend(false);
//			OwnerBean bean2=dao.update(bean);
//			System.out.println(bean2);
			
//			OwnerBean bean=dao.updateName("王", "大同", "pig");//update Name相關欄位資料
//			System.out.println(bean);
			
//			OwnerBean bean=dao.updateEmail("change3@gmail.com", "pig"); //update單一(Email欄位資料)
//			System.out.println(bean);
			
//			OwnerBean bean=dao.updatePwd("11111", "pig");    //update單一(密碼欄位資料)
//			System.out.println(bean);
							
			
//          boolean b=dao.delete("pig");
//          System.out.println("delete status:"+b);
			
			session.getTransaction().commit();
		}finally{
			HibernateUtil.closeSessionFactory();
		}
	}

}
