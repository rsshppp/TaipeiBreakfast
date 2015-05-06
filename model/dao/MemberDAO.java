package model.dao;

import java.util.List;

import model.bean.MemberBean;
import model.bean.ShopBean;

public interface MemberDAO {
//--*杜
	public abstract MemberBean insertMember(MemberBean bean);
	public abstract MemberBean updateMember(MemberBean bean);
	public abstract MemberBean selectMember(int MemberID);
	public abstract boolean changePassword(int MemberID,String memberPwd);
	public abstract List<MemberBean> selectMember();
	public abstract Boolean deleteMember(int MemberID);
	public abstract Boolean rebornMember(int MemberID);
//--*
	//會員查詢現存優惠 - Noah
	public abstract MemberBean selectSpecialPrice(Integer memberID);
	
	//會員獲得優惠 - Noah
	public abstract MemberBean getSpecialPrice(Integer memberID, Integer specialPriceID);
	
	//會員使用優惠 - Noah
	public abstract MemberBean useSpecialPrice(Integer memberID);
}
