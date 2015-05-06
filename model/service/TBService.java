package model.service;

import java.util.List;

import model.bean.MemberBean;
import model.bean.ShopBean;
import model.dao.MemberDAO;
import model.dao.ShopDAO;

public class TBService{

	//(-.-)*杜
	private MemberDAO member;
	private ShopDAO shop;
	private TBService(MemberDAO dao){
		this.member=dao;
	}
	private TBService(ShopDAO dao){
		this.shop=dao;
	}

	//(-.-)*杜
	public MemberBean insertMember(MemberBean bean) {
		return member.insertMember(bean);
	}

	//(-.-)*杜
	public MemberBean updateMember(MemberBean bean) {
		return member.updateMember(bean);
	}

	//(-.-)*杜
	public List<MemberBean> selectMember(MemberBean bean) {
		if(bean!=null){
			List<MemberBean> result=null;
			result.add(member.selectMember(bean.getMemberID()));
			return result;
		}
		return member.selectMember();
	}

	//(-.-)*杜
	public Boolean deleteMember(int MemberID) {
		return member.deleteMember(MemberID);
	}

	//(-.-)*杜
	public boolean changePassword(int MemberID,String memberPwd,String newMemPwd){
		boolean result=false;
		MemberBean bean= member.selectMember(MemberID);
		if(bean!=null){
			String testPwd=bean.getMemberPwd();
			if(testPwd==memberPwd){
				member.changePassword(MemberID, newMemPwd);
				result=true;
			}
		}
		return result;
	}
	
	//(-.-)*杜
	public void losepassword(int MemberID){
		MemberBean bean= member.selectMember(MemberID);
		if(bean!=null){
			String b="";
			for(int i=0;i<8;i++){
				long a=Math.round(Math.random()*9);
				b+=a;
			}
			member.changePassword(MemberID, b);
			// b 是新密碼,用Email寄給Member
			//回傳通知訊息到 view 給 Member 看
		}
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
