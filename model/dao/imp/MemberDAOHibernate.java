package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.bean.MemberBean;
import model.bean.OrderSumBean;
import model.dao.MemberDAO;

public class MemberDAOHibernate implements MemberDAO {

	public MemberDAOHibernate() {
	}
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	// (-.-)*杜
	@Override
	public MemberBean insertMember(MemberBean bean) {

		MemberBean result = new MemberBean();
		result.setMemberAcc(bean.getMemberAcc());
		result.setMemberPwd(bean.getMemberPwd());
		result.setMemberLastName(bean.getMemberLastName());
		result.setMemberFirstName(bean.getMemberFirstName());
		result.setMemberPhone(bean.getMemberPhone());
//		result.setMemberTel(bean.getMemberTel());
		result.setMemberEmail(bean.getMemberEmail());
		result.setMemberAddr(bean.getMemberAddr());
		result.setMemberImage(bean.getMemberImage());
		result.setMemberStatus(bean.getMemberStatus());
		result.setMemberSuspend(bean.getMemberSuspend());

		Session session = getSession();
		try {
			session.save(result);
		} catch (ConstraintViolationException ex) {
			System.out.println("Insert Data Error:" + ex.getMessage());
//			ex.fillInStackTrace();
		} catch (HibernateException gex) {
			System.out.println("Generic Error :" + gex.getMessage());
		}

		return result;
	}

	// (-.-)*杜
	@Override
	public MemberBean updateMember(MemberBean bean) {
		MemberBean result = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(MemberBean.class)
				.add(Restrictions.eq("memberID", bean.getMemberID()));
		Iterator upprod = criteria.list().iterator();
		MemberBean upper = (MemberBean) upprod.next();
		upper.setMemberAcc(bean.getMemberAcc());
		upper.setMemberEmail(bean.getMemberEmail());
		upper.setMemberLastName(bean.getMemberLastName());
		upper.setMemberFirstName(bean.getMemberFirstName());
		upper.setMemberPhone(bean.getMemberPhone());
		upper.setMemberTel(bean.getMemberTel());
		upper.setMemberAddr(bean.getMemberAddr());
//		upper.setMemberImage(bean.getMemberImage());

		session.saveOrUpdate(upper);
		result = upper;
		return result;
	}

	// (-.-)*杜
	@Override
	public boolean selectMemberByAcc(String mail) {
		boolean result = false;
		Query query = getSession().createQuery(
						"from MemberBean where memberAcc like:acc or memberEmail like:acc");
		query.setString("acc", mail);
		Iterator list = query.list().iterator();
		if (list.hasNext()) {
			MemberBean b=(MemberBean)list.next();
			System.out.println(b.getMemberEmail());
			result = true;
		}
		return result;
	}

	// (-.-)*杜
	@Override
	public MemberBean selectMemberByMail(String mail) {
		MemberBean result = null;
		Query query = getSession().createQuery(
						"from MemberBean where memberAcc like:acc or memberEmail like:acc");
		query.setString("acc", mail);
		Iterator list = query.list().iterator();
		if (list.hasNext()) {
			MemberBean b=(MemberBean)list.next();
			System.out.println(b.getMemberEmail());
			result = b;
		}
		return result;
	}

	// (-.-)*杜
	@Override
	public MemberBean selectMember(int MemberID) {
		Query query = getSession().createQuery("from MemberBean");
		query.setInteger("MemberID", MemberID);
		MemberBean result = (MemberBean)query.list().iterator().next();
//		MemberBean result = (MemberBean) getSession().get(MemberBean.class,MemberID);
		if (result.getMemberStatus() == true) {
			return result;
		}
		return null;
	}

	// (-.-)*杜
	// @Override
	// public List<MemberBean> selectMember() {
	// Query query =
	// getSession().createQuery("from MemberBean where MemberStatus=:st");
	// query.setInteger("st", 1);
	// return (List<MemberBean>) query.list();
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> selectMember() {
		Query query = getSession().createQuery("from MemberBean");
		// 抓資料時需要確認 MemberStatus
		List<MemberBean> result = null;
		if (query != null) {
			// query 100% 不會null , 即使其內容為空
			result = (List<MemberBean>) query.list();
		}
		return result;
	}

	// (-.-)*杜
	@Override
	public boolean changePassword(int MemberID, String memberPwd) {
		Boolean result = false;

//		Session session = getSession();
//		Transaction tx = session.beginTransaction();
//		Criteria criteria = session.createCriteria(MemberBean.class);
//		criteria.add(Restrictions.eq("MemberID", MemberID));
//		MemberBean mbean = (MemberBean) criteria.uniqueResult();
//		Iterator upprod = criteria.list().iterator();
//		while(upprod.hasNext()){
//			MemberBean upper = (MemberBean) upprod.next();
//			upper.setMemberPwd(memberPwd);
//			getSession().saveOrUpdate(upper);
//			result=true;
//		}
//		tx.commit();
		
		MemberBean mb = (MemberBean)getSession().get(MemberBean.class, MemberID);
		System.out.println(mb.getMemberAcc());
		if (mb != null) {
			mb.setMemberPwd(memberPwd);
			getSession().saveOrUpdate(mb);
			result = true;
		}
		
		return result;
	}
	
	// (-.-)*杜
	@Override
	public Boolean deleteMember(int MemberID) {
		Boolean result = false;
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(MemberBean.class);
		criteria.add(Restrictions.eq("MemberID", MemberID));
		Iterator<?> upprod = criteria.list().iterator();
		MemberBean upper = (MemberBean) upprod.next();
		// update MemberStatus
		if (upper != null) {
			upper.setMemberStatus(false);
			session.saveOrUpdate(upper);
			result = true;
			tx.commit();
		}
		if (session != null) {
			session.close();
		}
		return result;
	}

	// (-.-)*杜
	@Override
	public Boolean rebornMember(int MemberID) {
		Boolean result = false;
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(MemberBean.class);
		criteria.add(Restrictions.eq("MemberID", MemberID));
		Iterator<?> upprod = criteria.list().iterator();
		MemberBean upper = (MemberBean) upprod.next();
		// update MemberStatus 後台 & 測試 用
		if (upper != null) {
			upper.setMemberStatus(true);
			session.saveOrUpdate(upper);
			result = true;
			tx.commit();
		}
		if (session != null) {
			session.close();
		}
		return result;
	}

	// 查詢會員優惠價格 - Noah
	@Override
	public MemberBean selectSpecialPrice(Integer memberID) {
		return (MemberBean) this.getSession().get(MemberBean.class, memberID);
	}

	// 會員獲得優惠價格 - Noah
	@Override
	public boolean getSpecialPrice(Integer memberID, Integer specialPriceID) {

		Criteria criteria = getSession().createCriteria(MemberBean.class);

		criteria.add(Restrictions.eq("memberID", memberID));
		MemberBean mbean = (MemberBean) criteria.uniqueResult();
		mbean.setSpecialPriceID(specialPriceID);

		getSession().saveOrUpdate(mbean);

		return true;
	}

	// 會員使用優惠價格 - Noah
	@Override
	public boolean useSpecialPrice(Integer memberID) {

		Criteria criteria = getSession().createCriteria(MemberBean.class);

		criteria.add(Restrictions.eq("memberID", memberID));
		MemberBean mbean = (MemberBean) criteria.uniqueResult();
		mbean.setSpecialPriceID(null);

		getSession().saveOrUpdate(mbean);

		return true;
	}
}
