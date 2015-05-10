package model.UnitTest;

import static org.junit.Assert.*;

import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.service.OwnerService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OwnerServiceTestWeng {              //宗鈺-維護賣家資料
	private ApplicationContext context;
    private SessionFactory sessionFactory;
    private Session session;
    private OwnerService service;
    
	@Before
	public void setUp() throws Exception {
		context=new ClassPathXmlApplicationContext("beans.config.xml");
		sessionFactory=(SessionFactory)context.getBean("sessionFactory");
		session=sessionFactory.getCurrentSession();
		service=(OwnerService)context.getBean("ownerService");
		session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		session.getTransaction().commit();
		sessionFactory.close();
		((ConfigurableApplicationContext)context).close();
	}

	@Test
	public void testSelect() {
		OwnerBean ownerBean=new OwnerBean();          //select
		ownerBean.setOwnAcc("Laya");
		List<OwnerBean> list=service.select(ownerBean);    //參數可放null看看-設計上show全部
		System.out.println(list);
	}

//	@Test
	public void testGetShops() {
		OwnerBean ownerBean=new OwnerBean();          //getshops
		ownerBean.setOwnID(4);
		List<ShopBean> list=service.getShops(ownerBean);      
		System.out.println(list);
	}

//	@Test
	public void testInsert() {
		OwnerBean bean=new OwnerBean();            //insert
		bean.setOwnAcc("pig");
		bean.setOwnEmail("pig@gmail.com");
		bean.setOwnFirstName("Mary");
		bean.setOwnLastName("BBox");
		bean.setOwnPwd("456789");
		//bean.setOwnStatus(true); //已有預設
		//bean.setOwnSuspend(false);//已有預設
		if(service.insert(bean)){
			session.getTransaction().commit();  //review
			session=sessionFactory.getCurrentSession(); //review
			session.beginTransaction(); //review
			List<OwnerBean> list=service.select(bean);
			System.out.println(list);
		}else{
			System.out.println("insert失敗");
		}
	}

//	@Test
	public void testUpdate() {
		OwnerBean bean=new OwnerBean();              //update多項欄位資料,                      
		bean.setOwnAcc("pig");                       //OwnStatus,OwnSuspend值由其他方法更新
		bean.setOwnEmail("change2@gmail.com");
		bean.setOwnFirstName("Mary");
		bean.setOwnLastName("BBox");
		bean.setOwnPwd("456789");
		OwnerBean bean2=service.update(bean);
		System.out.println(bean2);
	}

//	@Test
	public void testDelete() {
		OwnerBean ownerBean=new OwnerBean();         //賣方刪帳號時所用的方法 //由session得知owner account
		ownerBean.setOwnAcc("pig");
		boolean b=service.delete(ownerBean);                 
        System.out.println("delete status:"+b); 
	}

//	@Test
	public void testSuspendOrCancel() {
		OwnerBean ownerBean=new OwnerBean();         //為後台管理停權所使用,可以停權,也可以取消停權
		ownerBean.setOwnAcc("pig");
		boolean b=service.suspendOrCancel(ownerBean);       
        System.out.println("停權設定是否成功:"+b);
	}

}
