package model.dao.imp;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import model.bean.MemberBean;
import model.bean.ShopBean;
import model.dao.MemberDAO;
import model.misc.HibernateUtil;

public class MemberDAOHibernate implements MemberDAO {
//杜
	private static Session getSession(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session;
	}
	
	@Override
	public MemberBean insertMember(MemberBean bean) {

		MemberBean result = new MemberBean();
		result.setMemberID(bean.getMemberID());
		result.setMemberAcc(bean.getMemberAcc());
		result.setMemberPwd(bean.getMemberPwd());
		result.setMemberLastName(bean.getMemberLastName());
		result.setMemberFirstName(bean.getMemberFirstName());
		result.setMemberPhone(bean.getMemberPhone());
		result.setMemberTel(bean.getMemberTel());
		result.setMemberEmail(bean.getMemberEmail());
		result.setMemberAddr(bean.getMemberAddr());
		result.setMemberImage(bean.getMemberImage());
		result.setMemberImageName(bean.getMemberImageName());
		result.setMemberStatus(bean.getMemberStatus());
		result.setMemberSuspend(bean.getMemberSuspend());
		result.setSpecialPriceID(bean.getSpecialPriceID());
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(result);
			tx.commit();
		} catch (ConstraintViolationException ex) {
			System.out.println("Insert Data Error:" + ex.getMessage());
		    ex.fillInStackTrace ();
			tx.rollback();
		} catch (HibernateException gex) {
			System.out.println("Generic Error :" + gex.getMessage());
			tx.rollback();
		}
		return result;
	}

	@Override
	public MemberBean updateMember(MemberBean bean) {
		MemberBean result = null;
		// MemberBean result = (MemberBean)
		// MemberBean.getSession().get(MemberBean.class, bean.getId());

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(MemberBean.class);
		criteria.add(Restrictions.eq("MemberID", bean.getMemberID()));
		Iterator upprod = criteria.list().iterator();
		MemberBean upper = (MemberBean) upprod.next();
		upper.setMemberAcc(bean.getMemberAcc());
		upper.setMemberPwd(bean.getMemberPwd());
		upper.setMemberLastName(bean.getMemberLastName());
		upper.setMemberFirstName(bean.getMemberFirstName());
		upper.setMemberPhone(bean.getMemberPhone());
		upper.setMemberTel(bean.getMemberTel());
		upper.setMemberEmail(bean.getMemberEmail());
		upper.setMemberAddr(bean.getMemberAddr());
		upper.setMemberImage(bean.getMemberImage());
		upper.setMemberImageName(bean.getMemberImageName());
		upper.setMemberStatus(bean.getMemberStatus());
		upper.setMemberSuspend(bean.getMemberSuspend());
		upper.setSpecialPriceID(bean.getSpecialPriceID());
		session.saveOrUpdate(upper);
		result = upper;
		tx.commit();
		if (session != null) {
			session.close();
			// System.out.println("session.close");// fortest
		}
		return result;
	}

	@Override
	public MemberBean selectMember(int MemberID) {
		return (MemberBean)getSession().get(MemberBean.class, MemberID);
	}

	@Override
	public List<MemberBean> selectMember() {
		Query query = getSession().createQuery("from MemberBean");
		List<MemberBean> result=null;
		if(query!=null){
			result = (List<MemberBean>) query.list();
		}
		return result;
	}

	@Override
	public MemberBean deleteMember(int MemberID) {
		//update MemberStatus
		return null;
	}

	@Override
	public List<ShopBean> selectShop(String keyword, int shopArea, int shopID) {
		//店鋪ID,店鋪所在城市,店鋪所在區域
		return null;
	}

	@Override
	public MemberBean selectSpecialPrice(Integer memberID) {
		return null;
	}

	@Override
	public MemberBean getSpecialPrice(Integer memberID, Integer specialPriceID) {
		return null;
	}

	@Override
	public MemberBean useSpecialPrice(Integer memberID) {
		return null;
	}

}