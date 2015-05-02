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
		OwnerBean bean=(OwnerBean)session.get(OwnerBean.class, ownerBean.getOwnID());
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
	
	
	public boolean delete(Integer ownID){
		OwnerBean bean=(OwnerBean)session.get(OwnerBean.class,ownID);
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
			
//			OwnerBean bean=dao.select("cweng777");    //select
//			System.out.println(bean);
			
//			List<OwnerBean> list=dao.selectAll();    //selectAll
//			System.out.println(list);
			
//			List<ShopBean> list=dao.getShops(2);      //getshops
//			System.out.println(list);
			
//			OwnerBean bean=new OwnerBean();           //insert
//			bean.setOwnAcc("pig");
//			bean.setOwnEmail("pig@gmail.com");
//			bean.setOwnFirstName("Mary");
//			bean.setOwnLastName("BBox");
//			bean.setOwnPwd("456789");
//			bean.setOwnStatus(true);
//			bean.setOwnSuspend(false);
//			OwnerBean bean2=dao.insert(bean);
//			System.out.println(bean2);
			
//			OwnerBean bean=new OwnerBean();           //update
//			bean.setOwnID(2);
//			bean.setOwnAcc("Alex");
//			bean.setOwnEmail("change@gmail.com");
//			bean.setOwnFirstName("john");
//			bean.setOwnLastName("mr");
//			bean.setOwnPwd("123");
//			bean.setOwnStatus(true);
//			bean.setOwnSuspend(false);
//			OwnerBean bean2=dao.update(bean);
//			System.out.println(bean2);
			
//            boolean b=dao.delete(1);
//            System.out.println("delete status:"+b);
			
			session.getTransaction().commit();
		}finally{
			HibernateUtil.closeSessionFactory();
		}
	}

}
