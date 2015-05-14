package model.service;

import java.util.ArrayList;
import java.util.List;

import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.dao.EmailDAO;
import model.dao.MemberDAO;
import model.dao.OrderDetailDAO;
import model.dao.OrderSumDAO;
import model.dao.ShopDAO;

public class TBService{

	//(-.-)*杜
	private MemberDAO member;
	private ShopDAO shop;
	private OrderSumDAO ordersum;
	private OrderDetailDAO orderdetail;
	private EmailDAO mail;

	//(-.-)*杜
	public MemberBean insertMember(MemberBean bean) {
		if(bean!=null){
			return member.insertMember(bean);
		}
		return null;
	}

	//(-.-)*杜
	public MemberBean updateMember(MemberBean bean) {
		if(bean!=null){
			return member.updateMember(bean);
		}
		return null;
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
		if(MemberID!=0){
			return member.deleteMember(MemberID);
		}
		return false;
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
			boolean aa=member.changePassword(MemberID, b);
			// b 是新密碼,用Email寄給Member
			if(aa=true){
				String membermail=bean.getMemberEmail();
				String first=bean.getMemberFirstName();
				boolean ab=mail.send(membermail, 
						"台北早餐通密碼變更", 
						"Dear "+first+" : \n\n 您的新密碼為 : "+b+" \n\n 請登入帳戶並修改密碼");
				//回傳通知訊息到 view 給 Member 看
				if(ab=true){
					//傳回"請收信"
				}else{
					//傳回信件發送失敗
				}
			}
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
	public List<OrderSumBean> selectOrdersByTime(int page) {
		// 顯示orderTime, 用orderSumID抓Detail
		return ordersum.queryOrderSumByTime(page);
	}
	
	//(-.-)*杜
	public List<OrderDetailBean> selectOrderDetail(Integer orderSumID) {
		//用orderSumID抓Detail, orderDetailID與orderSumID隱藏不顯示
		return orderdetail.queryOrderDetails(orderSumID);
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
