package model.dao;

import java.util.List;

import model.bean.MemberBean;

public interface MemberDAO {
	
	//--*杜
	public abstract MemberBean insertMember(MemberBean bean);
	public abstract MemberBean updateMember(MemberBean bean);
	public abstract boolean selectMemberByAcc(String mail);
	public abstract MemberBean selectMember(int MemberID);
	public abstract boolean changePassword(int MemberID,String memberPwd);
	public abstract List<MemberBean> selectMember();
	public abstract Boolean deleteMember(int MemberID);
	public abstract Boolean rebornMember(int MemberID);
	public abstract MemberBean selectMemberByMail(String mail);
	//--*
	
	//會員查詢現存優惠 - Noah
	public abstract MemberBean selectSpecialPrice(Integer memberID);
	
	//會員獲得優惠 - Noah
	public abstract boolean getSpecialPrice(Integer memberID, Integer specialPriceID);
	
	//會員使用優惠 - Noah
	public abstract boolean useSpecialPrice(Integer memberID);
	
	//停權<=>復權 by Steven
	public abstract boolean suspendOrCancel(String memberAcc);
}
