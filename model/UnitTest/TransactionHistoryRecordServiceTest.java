package model.UnitTest;

import static org.junit.Assert.*;

import java.util.List;

import model.bean.deliverValuesOnly.HistoryRecordBean;
import model.service.TransactionHistoryRecordService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionHistoryRecordServiceTest {             //宗鈺
	private ApplicationContext context;
    private SessionFactory sessionFactory;
    private Session session;
    private TransactionHistoryRecordService service;
    
	@Before
	public void setUp() throws Exception {
		context=new ClassPathXmlApplicationContext("beans.config.xml");
		sessionFactory=(SessionFactory)context.getBean("sessionFactory");
		session=sessionFactory.getCurrentSession();
		service=(TransactionHistoryRecordService)context.getBean("transactionHistoryRecordService");
		session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		session.getTransaction().commit();
		sessionFactory.close();
		((ConfigurableApplicationContext)context).close();
	}

	@Test
	public void testSelectHistoryRecord() {
		List<HistoryRecordBean> list=service.selectHistoryRecord(3, 4);
		System.out.println(list);
	}

}
