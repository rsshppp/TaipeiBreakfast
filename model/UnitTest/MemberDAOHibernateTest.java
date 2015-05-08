package UnitTest;

import static org.junit.Assert.*;
import model.bean.MemberBean;
import model.dao.DaysoffDAO;
import model.dao.MemberDAO;
import model.dao.imp.MemberDAOHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Utility.DefaultFactory;

public class MemberDAOHibernateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		SessionFactory sessionFactory = DefaultFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectMemberInt() {
		MemberDAO dao = new MemberDAOHibernate();
		MemberBean bean = dao.selectMember(1);
		System.out.println(bean.getMemberAcc());
		
	}

}
