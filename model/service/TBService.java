package model.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
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
	private SendMailSMTP mailD;

	public TBService(){
	}
	public TBService(MemberDAO member){
		this.member = member;
	}
	public void setMember(MemberDAO member) {
		this.member = member;
	}
	public void setShop(ShopDAO shop) {
		this.shop = shop;
	}
	public void setOrdersum(OrderSumDAO ordersum) {
		this.ordersum = ordersum;
	}
	public void setOrderdetail(OrderDetailDAO orderdetail) {
		this.orderdetail = orderdetail;
	}
	
	
	//(-.-)*杜
	public boolean CheackAcc(String mai){
		boolean result=false;
		//Ajax用mail檢查Acc有沒有重複
		if(mai!=null){
			boolean m=member.selectMemberByAcc(mai);
			if(m!=true){
				//if(false沒東西){ 允許進行下一步 sendCheackMail()}
				result=true;
			}
			// if(true有重複到){result=false}
		}
		return result;
	}
	
	//(-.-)*杜
	//需要能使用的mail才能註冊,考慮中的功能,很高的機率不會DEMO
	public boolean sendCheackMail(String ma){
		boolean result=false;
			//寄出mail驗證碼
		if(ma!=null){
			String r="";
			for(int i=0;i<5;i++){
				long a=Math.round(Math.random()*9);
				r+=a;
			}
			mailD.send(ma, "台北早餐通信箱驗證", "驗證碼 : "+r);
		}
		return result;
	}
	
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
	public MemberBean selectMemberE(String ea) {
	MemberBean result=null;
		if (ea != null) {
			MemberBean b = member.selectMemberByMail(ea);
			if (b != null) {
				result = b;
			}
		}
		System.out.println(result);
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
		MemberBean bean = member.selectMember(MemberID);
		if (bean != null) {
			String testPwd = bean.getMemberPwd();
			System.out.println(testPwd);
			if (memberPwd.equals(testPwd)) {
				member.changePassword(MemberID, newMemPwd);
				result = true;
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
				boolean ab=mailD.send(membermail, 
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
	public ShopBean selectSByID(int shopID) {
		if (shopID != 0) {
			return shop.select(shopID);
		}
		return null;
	}

	//(-.-)*杜
	public List<ShopBean> selectSByArea(String shopArea) {
		// Ajax 用 shopArea 找出 list 後選出 shopID
		List<ShopBean> result=null;
		if (shopArea != null) {
			result = shop.selectArea(shopArea);
		}
		//前端從BEAN中找Name
		return result;
	}
	
	//(-.-)*杜
	public List<ShopBean> selectSByKeyword(String keyword, String shopArea) {
		//店鋪ID,店鋪所在城市,店鋪所在區域
		List<ShopBean> result=null;

		// Ajax 用 shopArea 找出 list 後選出 shopID
		if (shopArea != null) {
			if (keyword != null) {
				// select shopArea 中符合keyword的部分
				result=shop.selectAK(shopArea, keyword);
			}
		}else{
			// select keyword from shopName,shopCity,shopArea,shopAddr,businessTimeNote
			if (keyword != null) {
				result = shop.selectKeyword(keyword);
				// 傳回ShopName,ShopCity,ShopArea, 保留ShopID
			}
		}
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
