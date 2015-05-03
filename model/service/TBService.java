package model.service;

import java.util.List;

import model.bean.MemberBean;
import model.bean.ShopBean;
import model.dao.TBMemberDAO;

public class TBService{

	private TBMemberDAO dao;
	private TBService(TBMemberDAO dao){
		this.dao=dao;
	}
	public MemberBean insertMember(MemberBean bean) {
		return dao.insertMember(bean);
	}

	public MemberBean updateMember(MemberBean bean) {
		return dao.updateMember(bean);
	}

	public List<MemberBean> selectMember(MemberBean bean) {
		if(bean!=null){
			List<MemberBean> result=null;
			result.add(dao.selectMember(bean));
			return result;
		}
		return dao.selectMember();
	}

	public MemberBean deleteMember(MemberBean bean) {
		return dao.deleteMember(bean.getMemberID());
	}

	public List<ShopBean> selectShop(String keyword, int shopArea, int shopID) {
		//店鋪ID,店鋪所在城市,店鋪所在區域
		return null;
	}

	public MemberBean select最新訂單() {
		//orderSumID,shopID,orderTime
		//orderDetailID,mealId,count,price
		return null;
	}
}
