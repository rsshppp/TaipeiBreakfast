package model.UnitTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import model.service.ReportService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReportServiceTest {                     //宗鈺
	private ApplicationContext context;
    private SessionFactory sessionFactory;
    private Session session;
    private ReportService service;
    
	@Before
	public void setUp() throws Exception {
		context=new ClassPathXmlApplicationContext("beans.config.xml");
		sessionFactory=(SessionFactory)context.getBean("sessionFactory");
		session=sessionFactory.getCurrentSession();
		service=(ReportService)context.getBean("reportService");
		session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		session.getTransaction().commit();
		sessionFactory.close();
		((ConfigurableApplicationContext)context).close();
	}

//	@Test
	public void testGetDailyReport() throws ParseException {  //日報表  //5,3也ok原因不知
		List<Object[]> list=service.getDailyReport(11, 2015, 5, 21); 
			Iterator ite=list.iterator();
			if(!ite.hasNext()){                        //action可參考
				System.out.println("輸入值不正確");
			}
			while(ite.hasNext()){
				Object[] item=(Object[])ite.next();
				System.out.print(item[0]+"  ");
				System.out.print(item[1]+" ");
				System.out.print(item[2]+" ");
				System.out.println(item[3]);
			}
			
		}
		

//	@Test
	public void testGetMonthlyReport() throws ParseException {  //月報表  //month要>0
		List<Object[]> list=service.getMonthlyReport(11, 2015, 5);
		Iterator ite=list.iterator();
		if(!ite.hasNext()){                        //action可參考
			System.out.println("輸入值不正確");
		}
		while(ite.hasNext()){
			Object[] item=(Object[])ite.next();
			System.out.print(item[0]+"  ");
			System.out.print(item[1]+" ");
			System.out.print(item[2]+" ");
			System.out.println(item[3]);
		}
	}
	
//	@Test
	public void testGetTimeReport() throws ParseException {   //特定店鋪當日不同時段報表
		List<Object> list=service.getTimeReport(11, 2015, 6, 23);
		System.out.println(list);
	}
	
	@Test
	public void testGetDetailTimeReport() throws ParseException {  //特定店鋪特定時段報表
		List<Object[]> list=service.getDetailTimeReport(12, 2015, 6, 23, 9);
		Iterator ite=list.iterator();
		if(!ite.hasNext()){                        //action可參考
			System.out.println("輸入值不正確");
		}
		while(ite.hasNext()){
			Object[] item=(Object[])ite.next();
			System.out.print(item[0]+"  ");
			System.out.print(item[1]+" ");
			System.out.print(item[2]+" ");
			System.out.println(item[3]);
		}
	}

}
