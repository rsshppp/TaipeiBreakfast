package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import model.bean.MemberBean;
import model.bean.ShopBean;
import model.dao.MemberDAO;
import model.misc.HibernateUtil;

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
//	private static Session getSession() {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		return session;
//	}

	// (-.-)*杜--未檢查
	@Override
	public MemberBean insertMember(MemberBean bean) {
		//--未檢查
		MemberBean result = new MemberBean();
		result.setMemberAcc(bean.getMemberAcc());
		result.setMemberPwd(bean.getMemberPwd());
		result.setMemberLastName(bean.getMemberLastName());
		result.setMemberFirstName(bean.getMemberFirstName());
		result.setMemberPhone(bean.getMemberPhone());
		result.setMemberTel(bean.getMemberTel());
		result.setMemberEmail(bean.getMemberEmail());
		result.setMemberAddr(bean.getMemberAddr());
		result.setMemberImage(bean.getMemberImage());

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(result);
			tx.commit();
		} catch (ConstraintViolationException ex) {
			System.out.println("Insert Data Error:" + ex.getMessage());
			ex.fillInStackTrace();
			tx.rollback();
		} catch (HibernateException gex) {
			System.out.println("Generic Error :" + gex.getMessage());
			tx.rollback();
		}
		return result;
	}

	// (-.-)*杜
	@Override
	public MemberBean updateMember(MemberBean bean) {
		//--未檢查
		MemberBean result = null;
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(MemberBean.class);
		criteria.add(Restrictions.eq("MemberID", bean.getMemberID()));
		Iterator upprod = criteria.list().iterator();
		MemberBean upper = (MemberBean) upprod.next();
		upper.setMemberAcc(bean.getMemberAcc());
		upper.setMemberLastName(bean.getMemberLastName());
		upper.setMemberFirstName(bean.getMemberFirstName());
		upper.setMemberPhone(bean.getMemberPhone());
		upper.setMemberTel(bean.getMemberTel());
		upper.setMemberEmail(bean.getMemberEmail());
		upper.setMemberAddr(bean.getMemberAddr());
		upper.setMemberImage(bean.getMemberImage());
		session.saveOrUpdate(upper);
		result = upper;
		tx.commit();
		if (session != null) {
			session.close();
		}
		return result;
	}

	// (-.-)*杜
	@Override
	public boolean selectMemberByAcc(String mail) {
		boolean result = false;
		Query query=getSession().createQuery("from MemberBean where memberAcc like:acc or memberEmail like:acc");
		query.setString("acc", "%"+mail+"%");
		Iterator list=query.list().iterator();
		if(list.hasNext()) {
//			MemberBean b=(MemberBean)list.next();
//			System.out.println(b.getMemberEmail());
			result=true;
		}
		return result;
	}

	// (-.-)*杜
	@Override
	public MemberBean selectMember(int MemberID) {
		MemberBean result = (MemberBean) getSession().get(MemberBean.class,MemberID);
		if (result.getMemberStatus() == true) {
			return result;
		}
		return null;
	}

	// (-.-)*杜
	@Override
	public List<MemberBean> selectMember() {
		Query query = getSession().createQuery("from MemberBean where MemberStatus=:st");
		query.setInteger("st", 1);
		List<MemberBean> result = null;
		result = (List<MemberBean>) query.list();
		return result;
	}

	// (-.-)*杜
	@Override
	public boolean changePassword(int MemberID, String memberPwd) {
		Boolean result = false;
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
//		Criteria criteria = session.createCriteria(MemberBean.class);
//		criteria.add(Restrictions.eq("MemberID", MemberID));
//		Iterator upprod = criteria.list().iterator();
//		MemberBean upper = (MemberBean) upprod.next();
//		upper.setMemberPwd(memberPwd);
//		session.saveOrUpdate(upper);
//		result = true;

		MemberBean mb = this.selectMember(MemberID);
		if (mb != null) {
			Query query= session.createQuery("UPDATE MemberBean SET MemberPwd=:Pwd WHERE MemberID=:ID");
			query.setInteger("ID", MemberID);
			query.setString("Pwd", memberPwd);
			query.executeUpdate();
			result = true;
		}

		tx.commit();
		if (session != null) {
			session.close();
		}
		return result;
	}

	// (-.-)*杜
	@Override
	public Boolean deleteMember(int MemberID) {
		Boolean result = false;
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		// update MemberStatus
		
//		Criteria criteria = session.createCriteria(MemberBean.class);
//		criteria.add(Restrictions.eq("MemberID", MemberID));
//		Iterator upprod = criteria.list().iterator();
//		MemberBean upper = (MemberBean) upprod.next();
//		if (upper != null) {
//			upper.setMemberStatus(false);
//			session.saveOrUpdate(upper);
//			result = true;
//		}

		MemberBean mb = this.selectMember(MemberID);
		if (mb != null) {
			Query query= session.createQuery("UPDATE MemberBean SET MemberStatus=:st WHERE MemberID=:ID");
			query.setInteger("ID", MemberID);
			query.setBoolean("st", false);
			query.executeUpdate();
			result = true;
		}
		
		tx.commit();
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
		// update MemberStatus 後台 & 測試 用
		
//		Criteria criteria = session.createCriteria(MemberBean.class);
//		criteria.add(Restrictions.eq("MemberID", MemberID));
//		Iterator upprod = criteria.list().iterator();
//		MemberBean upper = (MemberBean) upprod.next();
//		if (upper != null) {
//			upper.setMemberStatus(true);
//			session.saveOrUpdate(upper);
//			result = true;
//		}

		MemberBean mb = this.selectMember(MemberID);
		if (mb != null) {
			Query query= session.createQuery("UPDATE MemberBean SET MemberStatus=:st WHERE MemberID=:ID");
			query.setInteger("ID", MemberID);
			query.setBoolean("st", true);
			query.executeUpdate();
			result = true;
		}
		
		tx.commit();
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

		String hql = "UPDATE MemberBean as MemberBean SET specialPriceID = :specialPriceID "
				+ "WHERE MemberBean.memberID = :memberID";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("specialPriceID", specialPriceID);
		query.setParameter("memberID", memberID);
		int result = query.executeUpdate();
		// System.out.println("Rows affected: " + result);

		if (result != 0) {
			return true;
		}
		return false;
	}

	// 會員使用優惠價格 - Noah
	@Override
	public boolean useSpecialPrice(Integer memberID) {

		String hql = "UPDATE MemberBean as MemberBean SET specialPriceID = :specialPriceID "
				+ "WHERE MemberBean.memberID = :memberID";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("specialPriceID", null);
		query.setParameter("memberID", memberID);
		int result = query.executeUpdate();
		// System.out.println("Rows affected: " + result);

		if (result != 0) {
			return true;
		}
		return false;
	}

}
