package controller;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import result.MealBeanInfo;
import result.ShopBeanInfo;
import result.SpecialPriceBeanInfo;
import model.bean.MealBean;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.bean.SpecialPriceBean;
import model.service.MealService;
import model.service.SpecialPriceService;

public class OwnerSpecialPriceAction {
	private SpecialPriceService specialPriceService;
	private MealService mealService;
	
	//Struts2傳入參數
	private Integer ownID;
	private Integer shopID;
	private Integer mealID;
	private Integer specialPrice;
	private String startDate;
	private String endDate;
	private Integer specialPriceID;
	
	//Struts2傳出
	private List<ShopBeanInfo> list;
	private List<MealBeanInfo> mlist;
	private List<SpecialPriceBeanInfo> slist;
	private String result;
	private InputStream inputStream;
	
	public List<SpecialPriceBeanInfo> getSlist() {
		return slist;
	}
	public void setSpecialPriceService(SpecialPriceService specialPriceService) {
		this.specialPriceService = specialPriceService;
	}
	public void setOwnID(Integer ownID) {
		this.ownID = ownID;
	}
	public List<ShopBeanInfo> getList() {
		return list;
	}
	public void setMealService(MealService mealService) {
		this.mealService = mealService;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	public List<MealBeanInfo> getMlist() {
		return mlist;
	}
	public void setMealID(Integer mealID) {
		this.mealID = mealID;
	}
	public void setSpecialPrice(Integer specialPrice) {
		this.specialPrice = specialPrice;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public String getResult() {
		return result;
	}
	public void setSpecialPriceID(Integer specialPriceID) {
		this.specialPriceID = specialPriceID;
	}
	public String queryShops(){
		OwnerBean obean = new OwnerBean();
		obean.setOwnID(ownID);
		List<ShopBean> slist = specialPriceService.queryShops(obean);
		ListIterator<ShopBean> iterator = slist.listIterator();
		ShopBeanInfo sbeani;
		//將資料做整理回傳
		list = new ArrayList<ShopBeanInfo>();
		while(iterator.hasNext()){
			ShopBean sbean = iterator.next();
			sbeani = new ShopBeanInfo();
			sbeani.setOwnID(sbean.getOwnID());
			sbeani.setShopCondID(sbean.getShopCondID());
			sbeani.setShopID(sbean.getShopID());
			sbeani.setShopName(sbean.getShopName());
			sbeani.setShopSuspend(sbean.getShopSuspend());
			list.add(sbeani);
		}
		
		return "queryShops";
	}
	public String selectShopMeal(){
		List<MealBean> templist = mealService.selectShopMeal(shopID);
		Iterator<MealBean> iterator = templist.iterator();
		MealBeanInfo mealBeanInfo;
		mlist = new ArrayList<MealBeanInfo>();
		while(iterator.hasNext()){
			MealBean mbean = iterator.next();
			mealBeanInfo = new MealBeanInfo();
			mealBeanInfo.setMealID(mbean.getMealID());
			mealBeanInfo.setMealKindID(mbean.getMealKindID());
			mealBeanInfo.setMealName(mbean.getMealName());
			mealBeanInfo.setMealStatus(mbean.getMealStatus());
			mealBeanInfo.setPrice(mbean.getPrice());
			mealBeanInfo.setShopName(mbean.getShopBean().getShopName());
			mlist.add(mealBeanInfo);
		}
		mlist.sort(new Comparator<MealBeanInfo>() {

			@Override
			public int compare(MealBeanInfo o1, MealBeanInfo o2) {
				if(o1.getMealKindID()>o2.getMealKindID()){
					return 1;
				}
				if(o1.getMealKindID()==o2.getMealKindID()){
					if(o1.getMealID() > o2.getMealID()){
						return 0;
					}
				}
				return -1;
			}
			
		});
		return "selectShopMeal";
	}
	
	public String insertSpecialPrice(){
		SpecialPriceBean bean = new SpecialPriceBean();
		MealBean mbean = new MealBean();
		mbean.setMealID(mealID);
		bean.setMealBean(mbean);
		bean.setSpecialPrice(specialPrice);
		bean.setStartDate(java.sql.Date.valueOf(startDate));
		bean.setEndDate(java.sql.Date.valueOf(endDate));
		boolean b= specialPriceService.insertSpecialPrice(bean);
		System.out.println("insertSpecialPrice="+b);
		if(b){
			result = "true";
		}else{
			result="false";
		}
		this.inputStream = new StringBufferInputStream(this.result);
		return "insertSpecialPrice";
	}
	
	public String querySpecialPrice(){
		OwnerBean obean = new OwnerBean();
		obean.setOwnID(ownID);
		List<SpecialPriceBean> slist = specialPriceService.querySpecialPrice(obean);
		Iterator<SpecialPriceBean> iterator = slist.iterator();
		this.slist = new ArrayList<SpecialPriceBeanInfo>();
		SpecialPriceBeanInfo sbeani;
		MealBeanInfo mbeani;
		while(iterator.hasNext()){
			SpecialPriceBean sbean = iterator.next();
			sbeani = new SpecialPriceBeanInfo();
			mbeani = new MealBeanInfo();
			mbeani.setShopName(sbean.getMealBean().getShopBean().getShopName());
			mbeani.setMealName(sbean.getMealBean().getMealName());
			sbeani.setMealBeanInfo(mbeani);
			sbeani.setEndDate(sbean.getEndDate());
			sbeani.setMealID(sbean.getMealID());
			sbeani.setSpecialPrice(sbean.getSpecialPrice());
			sbeani.setStartDate(sbean.getStartDate());
			sbeani.setSpecialPriceID(sbean.getSpecialPriceID());
			this.slist.add(sbeani);
		}
		
		return "querySpecialPrice";
	}
	
	public String deleteSpecialPrice(){
		SpecialPriceBean bean = new SpecialPriceBean();
		bean.setSpecialPriceID(specialPriceID);
		boolean b = specialPriceService.deleteSpecialPrice(bean);
		if(b){
			result = "true";
		}else{
			result="false";
		}
		this.inputStream = new StringBufferInputStream(this.result);
		return "deleteSpecialPrice";
	}
}
