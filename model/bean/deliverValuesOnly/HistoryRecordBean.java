package model.bean.deliverValuesOnly;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;  //測試用到的
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HistoryRecordBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderSumID;
	private String shopName;       //店鋪名字  from ShopBean  <=
	private String memberAcc;         //會員帳號  from MemberBean <=
	private Double totalPrice;     //總價格      from OrderSumBean
	private Timestamp orderTime;   //訂單成立時間 from OrderSumBean
	private Integer starsForOwn;   //會員給予星數 from OrderSumBean
	private String evaluateForShop;//會員給予評價 from OrderSumBean
	
	private Set<HistoryOrderDetailBean> HistoryOrderDetailBean;//交易歷史專用之訂單明細
	                                                            //from HistoryOrderDetailBean
	
	public HistoryRecordBean(){
		
	}

	@Override
	public String toString() {
		return "{ orderSumID:"+orderSumID+", shopName:"+shopName+", memberAcc:"+memberAcc+
				", totalPrice:"+totalPrice+", orderTime:"+orderTime+", starsForOwn:"
				+starsForOwn+", evaluateForShop:"+evaluateForShop+", HistoryOrderDetailBean:"+HistoryOrderDetailBean+" }";
	}
	
	public Integer getOrderSumID() {
		return orderSumID;
	}

	public void setOrderSumID(Integer orderSumID) {
		this.orderSumID = orderSumID;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getMemberAcc() {
		return memberAcc;
	}

	public void setMemberAcc(String memberAcc) {
		this.memberAcc = memberAcc;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getStarsForOwn() {
		return starsForOwn;
	}

	public void setStarsForOwn(Integer starsForOwn) {
		this.starsForOwn = starsForOwn;
	}

	public String getEvaluateForShop() {
		return evaluateForShop;
	}

	public void setEvaluateForShop(String evaluateForShop) {
		this.evaluateForShop = evaluateForShop;
	}

	public Set<HistoryOrderDetailBean> getHistoryOrderDetailBean() {
		return HistoryOrderDetailBean;
	}

	public void setHistoryOrderDetailBean(
			Set<HistoryOrderDetailBean> historyOrderDetailBean) {
		HistoryOrderDetailBean = historyOrderDetailBean;
	}
	
	
	public static void main(String[] args) {   //測試用
		HistoryOrderDetailBean bean=new HistoryOrderDetailBean();
		bean.setCount(2);
		HistoryOrderDetailBean bean2=new HistoryOrderDetailBean();
		bean2.setPrice(40.0);
		Set set=new HashSet();
		set.add(bean);
		set.add(bean2);
		HistoryRecordBean historyBean=new HistoryRecordBean();
		historyBean.setHistoryOrderDetailBean(set);
		System.out.println(historyBean);

	}

}
