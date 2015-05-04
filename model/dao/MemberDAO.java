package model.dao;

import model.bean.MemberBean;

public interface MemberDAO {
	//會員查詢現存優惠 - Noah
	public abstract MemberBean selectSpecialPrice(Integer memberID);
	
	//會員獲得優惠 - Noah
	public abstract MemberBean getSpecialPrice(Integer memberID, Integer specialPriceID);
	
	//會員使用優惠 - Noah
	public abstract MemberBean useSpecialPrice(Integer memberID);
}
