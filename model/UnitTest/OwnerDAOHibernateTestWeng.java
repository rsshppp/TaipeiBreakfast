package model.UnitTest;

import static org.junit.Assert.*;

import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.OwnerDAO;
import model.dao.imp.OwnerDAOHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OwnerDAOHibernateTestWeng {           //宗鈺
	private ApplicationContext context;
    private SessionFactory sessionFactory;
    private Session session;
    private OwnerDAO dao;
    
	@Before
	public void setUp() throws Exception {
		context=new ClassPathXmlApplicationContext("beans.config.xml");
		sessionFactory=(SessionFactory)context.getBean("sessionFactory");
		session=sessionFactory.getCurrentSession();
		dao=(OwnerDAOHibernate)context.getBean("ownerDAOHibernate");
		session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		session.getTransaction().commit();
		sessionFactory.close();
		((ConfigurableApplicationContext)context).close();
	}

//	@Test
	public void testSelect() {
		OwnerBean bean=dao.select("Laya");    //select
		System.out.println(bean);
	}

	@Test
	public void testSelectAll() {
		List<OwnerBean> list=dao.selectAll();    //selectAll
		System.out.println(list);
	}

//	@Test
	public void testGetShops() {
		List<ShopBean> list=dao.getShops(4);      //getshops
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
		if(dao.insert(bean)){
			session.getTransaction().commit();
			session=sessionFactory.getCurrentSession();
			session.beginTransaction();
			dao=(OwnerDAOHibernate)context.getBean("ownerDAOHibernate");
			OwnerBean bean2=dao.select(bean.getOwnAcc());
			System.out.println(bean2);
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
		OwnerBean bean2=dao.update(bean);
		System.out.println(bean2);
	}

//	@Test
	public void testUpdateName() {
		OwnerBean bean=dao.updateName("王", "大同", "pig");//update Name相關欄位資料
		System.out.println(bean);
	}

//	@Test
	public void testUpdateEmail() {
		OwnerBean bean=dao.updateEmail("change3@gmail.com", "pig"); //update單一(Email欄位資料)
		System.out.println(bean);
	}

//	@Test
	public void testUpdatePwd() {
		OwnerBean bean=dao.updatePwd("11111", "pig");    //update單一(密碼欄位資料)
		System.out.println(bean);;
	}

//	@Test
	public void testDelete() {
       boolean b=dao.delete("pig");                 //賣方刪帳號時所用的方法 //由session得知owner account
       System.out.println("delete status:"+b);
	}

//	@Test
	public void testSuspendOrCancel() {
       boolean b=dao.suspendOrCancel("pig");       //為後台管理停權所使用,可以停權,也可以取消停權
       System.out.println("停權設定是否成功:"+b);
	}

}
