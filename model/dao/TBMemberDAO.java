package model.dao;

import java.util.List;

import model.bean.MemberBean;
import model.bean.ShopBean;

public interface TBMemberDAO {
	public abstract MemberBean insertMember(MemberBean bean);
	public abstract MemberBean updateMember(MemberBean bean);
	public abstract MemberBean selectMember(MemberBean bean);
	public abstract List<MemberBean> selectMember();
	public abstract MemberBean deleteMember(int MemberID);
	public abstract List<ShopBean> selectShop(String keyword,int shopArea,int shopID);
}
