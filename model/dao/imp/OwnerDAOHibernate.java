package model.dao.imp;

import java.util.Iterator;              
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.OwnerDAO;
import model.misc.HibernateUtil;
                                                       //by宗鈺
                                                       //沒做關連
                                                       //配合資料庫TaipeiBreakfast_20150504版本
public class OwnerDAOHibernate implements OwnerDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public OwnerBean select(String ownerAccount){        //此方法含濾掉status為false之賣家,後台管理用時只需show status為true之賣家
//		Query query=this.getSession().createQuery("from OwnerBean as OwnerBean where OwnerBean.OwnAcc=:account"); //有別名."OwnAcc"一定要follow對應檔
		Query query=this.getSession().createQuery("from OwnerBean where ownAcc=:account");//ownAcc之"o"大小寫ok
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
//		Query query=this.getSession().createQuery("from OwnerBean where ownAcc=:account");
//		query.setString("account", ownerAccount);
//		Iterator list=query.list().iterator();
//		if(list.hasNext()){
//			OwnerBean bean=(OwnerBean)list.next();
//			return bean;
//		}
//		return null;
//	}
	
	public List<OwnerBean> selectAll(){
		Query query=this.getSession().createQuery("from OwnerBean where ownStatus=:status");
		query.setBoolean("status", true);
		return (List<OwnerBean>) query.list();
	}
	
	
	
	public List<ShopBean> getShops(Integer ownID){
		Query query=this.getSession().createQuery("from ShopBean where ownID=:ownID");
		query.setInteger("ownID", ownID);
		return (List<ShopBean>) query.list();
	}
	
	
	public boolean insert(OwnerBean ownerBean){
		OwnerBean bean=this.select(ownerBean.getOwnAcc());
//		System.out.println(ownerBean);
		if(bean==null){
			this.getSession().save(ownerBean);
			return true;
		}
		return false;
	}
	
	
	public OwnerBean update(OwnerBean ownerBean){
		OwnerBean bean=this.select(ownerBean.getOwnAcc());
		if(bean!=null){
			bean.setOwnEmail(ownerBean.getOwnEmail());
			bean.setOwnFirstName(ownerBean.getOwnFirstName());
			bean.setOwnLastName(ownerBean.getOwnLastName());

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
	
	
	public boolean delete(Integer ownID){
		OwnerBean bean=this.select(ownID);
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

	public OwnerBean select(Integer ownID){        //此方法含濾掉status為false之賣家,後台管理用時只需show status為true之賣家
		OwnerBean bean=(OwnerBean)this.getSession().get(OwnerBean.class,ownID);
		if(bean.getOwnStatus()==true){                 //此處過濾
			return bean;
		}		
		return null;
	}
}
