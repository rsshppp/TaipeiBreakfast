package model.service;

import java.util.List;

import model.bean.MemberBean;
import model.bean.ShopBean;
import model.dao.MemberDAO;

public class TBService{

	//(-.-)*杜
	private MemberDAO dao;
	private TBService(MemberDAO dao){
		this.dao=dao;
	}

	//(-.-)*杜
	public MemberBean insertMember(MemberBean bean) {
		return dao.insertMember(bean);
	}

	//(-.-)*杜
	public MemberBean updateMember(MemberBean bean) {
		return dao.updateMember(bean);
	}

	//(-.-)*杜
	public List<MemberBean> selectMember(MemberBean bean) {
		if(bean!=null){
			List<MemberBean> result=null;
			result.add(dao.selectMember(bean.getMemberID()));
			return result;
		}
		return dao.selectMember();
	}

	//(-.-)*杜
	public Boolean deleteMember(int MemberID) {
		return dao.deleteMember(MemberID);
	}

	//(-.-)*杜
	public List<ShopBean> selectShop(String keyword, int shopArea, int shopID) {
		//店鋪ID,店鋪所在城市,店鋪所在區域
		return null;
	}

	//(-.-)*杜
	public MemberBean select最新訂單() {
		//orderSumID,shopID,orderTime
		//orderDetailID,mealId,count,price
		return null;
	}
}
