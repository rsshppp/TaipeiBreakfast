package model.service;

import java.util.ArrayList;
import java.util.List;

import model.bean.MemberBean;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.MemberDAO;
import model.dao.OwnerDAO;
import model.dao.ShopDAO;

public class TBService{

	//(-.-)*杜
	private MemberDAO member;
	private ShopDAO shop;

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
		List<MemberBean> result=null;
		if(bean!=null && bean.getMemberID()!=0){
			MemberBean a=member.selectMember(bean.getMemberID());
			if(a!=null){
			result = new ArrayList<MemberBean>();
			result.add(a);
			}
		}else{
			result= member.selectMember();
		}
		return result;
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
	public List<ShopBean> selectShop(String keyword, String shopArea, int shopID) {
		//店鋪ID,店鋪所在城市,店鋪所在區域
		List<ShopBean> result=null;
		// select keyword from shopName,shopCity,shopArea
		if(keyword!=null){
			result=shop.selectKeyword(keyword);
		}
		if(shopID!=0){
			// Ajax 用 shopArea 找出 list 後選出 shopID
			result.add(shop.select(shopID));
		}
		// select shopArea 傳回 list
		result=shop.selectArea(shopArea);
		
		return result;
	}

	//(-.-)*杜
	public String selectNews() {
		//shopID,orderTime
		
		//orderDetailID,mealId,count,price
		
		return null;
	}

	//(-.-)*杜
	public List<ShopBean> selectAllowShop(){
		List<ShopBean> a = shop.allowNeedsShop();
		return a;
	}

	//(-.-)*杜
	public boolean allowShop(int ShopID){
		return shop.allowShop(ShopID);
	}
	
}
