package model.dao;

import java.util.List;

import model.Bean.MemberBean;
import model.Bean.ShopBean;

public interface TBMemberDAO {
	public abstract MemberBean insertMember(MemberBean bean);
	public abstract MemberBean updateMember(MemberBean bean);
	public abstract MemberBean selectMember(MemberBean bean);
	public abstract List<MemberBean> selectMember();
	public abstract MemberBean deleteMember(int MemberID);
	public abstract List<ShopBean> select店鋪(String keyword,int shopArea,int shopID);
	public abstract List<String> select最新訂單();
}
