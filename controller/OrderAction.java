package controller;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

import result.OrderInfo;
import result.ShopBeanInfo;
import result.TimeTypeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.bean.MealBean;
import model.bean.MealKindListBean;
import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.ShopBean;
import model.bean.SpecialPriceBean;
import model.service.MealService;
import model.service.MemberFunctionService;
import model.service.OrderSumService;
import model.service.ShopService;
import model.service.SpecialPriceService;

public class OrderAction {
	private OrderSumService orderSumService;
	private SpecialPriceService specialPriceService;
	private MealService mealService;
	private ShopService shopService;
	private MemberFunctionService memberFunctionService;
	private Integer shopID;
	private Integer mealKindID;
	private Integer page;
	private Integer mealID;
	private Integer memberID;
	//list回傳結果
	private List<MealBean> mealBeanlist;
	private List<Map<String, Object>> mealKindlist;
	private List<ShopBeanInfo> sbeanlist;
	//圖片以String傳回
	private String encodedText;
	private InputStream inputStream;
	private byte[] imageByte;
	//優惠券傳回
	private List<SpecialPriceBean> specialPriceBean;
	//前端傳回一筆訂單字串為JSON
	private String orderString;
	
	public List<ShopBeanInfo> getSbeanlist() {
		return sbeanlist;
	}

	public void setMemberFunctionService(MemberFunctionService memberFunctionService) {
		this.memberFunctionService = memberFunctionService;
	}

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}

	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}

	public void setMealService(MealService mealService) {
		this.mealService = mealService;
	}

	public void setSpecialPriceService(SpecialPriceService specialPriceService) {
		this.specialPriceService = specialPriceService;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public List<SpecialPriceBean> getSpecialPriceBean() {
		return specialPriceBean;
	}

	public String getCustomContentType() {
		return "image/jpg";
	}
	
	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getEncodedText() {
		return encodedText;
	}

	public void setMealID(Integer mealID) {
		this.mealID = mealID;
	}

	public List<Map<String, Object>> getMealKindlist() {
		return mealKindlist;
	}

	public List<MealBean> getMealBeanlist() {
		return mealBeanlist;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setMealKindID(Integer mealKindID) {
		this.mealKindID = mealKindID;
	}
	
	public void setOrderSumService(OrderSumService orderSumService) {
		this.orderSumService = orderSumService;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	
	
	//查詢某間店家餐點種類，需要shopID
	public String queryMealKindList(){
		mealKindlist = new ArrayList<Map<String, Object>>(); 
		ShopBean bean =new ShopBean();
		bean.setShopID(shopID);
		List<MealKindListBean> sresult = orderSumService.queryMealKindList(bean);
		//先將方法傳回結果做好排序
		Collections.sort(sresult, new Comparator<MealKindListBean>(){
			@Override
			public int compare(MealKindListBean o1, MealKindListBean o2) {
				return o1.getMealKindID()-o2.getMealKindID();
			}
		});
		Iterator<MealKindListBean> iterator = sresult.iterator();
		while(iterator.hasNext()){
			MealKindListBean mklbean = iterator.next();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mealKindID", mklbean.getMealKindID());
			map.put("mealKindName", mklbean.getMealKindName());
			mealKindlist.add(map);
		}
//		System.out.println("jSONString="+jSONString);
		return "mealKindLists";
	}
	//透過shopID,mealKindList,page
	public String queryMealBean(){
		Long l=System.currentTimeMillis();
		List<MealBean> list1 = orderSumService.selectShopMeal(shopID, mealKindID);
		mealBeanlist =new ArrayList<MealBean>();
		Iterator iterator = list1.iterator();
		while(iterator.hasNext()){
			//將MealImage設為Null加快傳輸，建立新的Bean存入查詢到Bean的資料
			MealBean temp = (MealBean) iterator.next();
			if(temp.getMealStatus()==true){
				MealBean newbean = new MealBean();
				newbean.setMealID(temp.getMealID());
				newbean.setMealKindID(temp.getMealKindID());
				newbean.setMealName(temp.getMealName());
				newbean.setMealStatus(temp.getMealStatus());
				newbean.setPrice(temp.getPrice());
				newbean.setShopID(temp.getShopID());
				mealBeanlist.add(newbean);
			}
		}
		System.out.println(mealBeanlist);
		Long l1=System.currentTimeMillis();
		System.out.println((l1-l));
		return "specificMeal";
	}
	
	//選出圖片資料
	public String queryMealImage(){
		if(orderSumService.queryMealImage(mealID) !=null){
			imageByte = orderSumService.queryMealImage(mealID);
		}else{
			imageByte = mealService.selectMealByMealID(mealID).getMealKindListBean().getDefaultImage();
		}
		//編碼
//		final Base64.Encoder encoder = Base64.getEncoder();
//		encodedText = encoder.encodeToString(b);
//		encodedText = "data:image/jpg;base64, "+encodedText;
//		this.inputStream = new StringBufferInputStream(encodedText);
//		System.out.println(encodedText);
		return "Image";
	}

	
	//傳回會員的優惠券(會員只有0或1張優惠券)
	public String queryMemberSpecialPriceBean(){
		MemberBean mbean = new MemberBean();
//		System.out.println("memberID="+memberID);
		mbean.setMemberID(memberID);
		SpecialPriceBean sbean = specialPriceService.querySpecialPrice(mbean);
//		System.out.println("sbean="+sbean);
		this.specialPriceBean = new ArrayList<SpecialPriceBean>();
		specialPriceBean.add(sbean);
		return "special";
	}
	
	public String queryShopMeals(){
		List<MealBean> result = mealService.selectShopMeal(shopID);
		Iterator iterator = result.iterator();
		MealBean temp;
		mealBeanlist = new ArrayList<MealBean>();
		while(iterator.hasNext()){
			MealBean mbean = (MealBean) iterator.next();
			if(mbean.getMealStatus()==true){
				temp = new MealBean();
				temp.setMealID(mbean.getMealID());
				temp.setMealName(mbean.getMealName());
				temp.setPrice(mbean.getPrice());
				temp.setShopID(mbean.getShopID());
				mealBeanlist.add(temp);
			}
		}
		return "meals";
	}
	
	public String newOrder(){
		Gson gson2 = new GsonBuilder().registerTypeAdapter(Time.class, new TimeTypeAdapter())
			.setDateFormat("HH-mm-ss").create();
		OrderInfo info = gson2.fromJson(orderString, OrderInfo.class);
		System.out.println(info.getExpectTime());
		//製作新增訂單要的Bean
		OrderSumBean osbean = new OrderSumBean();
		ShopBean sbean = shopService.select(info.getShopID());
		MemberBean memberBean = orderSumService.selectMemberByMemberID(info.getMemberID());
		MealBean mbean;
		OrderDetailBean dbean;
		String[] items = info.getItems();
		double smallprice =0.0;
		for(String temp : items){
			String[] temparray = temp.split(",");
			int mealID = Integer.parseInt(temparray[0]);
			int count = Integer.parseInt(temparray[1]);
			double price = Double.parseDouble(temparray[2]);
			smallprice += count*price;
			mbean = mealService.selectMealByMealID(mealID);
			dbean = new OrderDetailBean();
			dbean.setCount(count);
			dbean.setMealBean(mbean);
			dbean.setPrice(price);
			dbean.setOrderSumBean(osbean);
			osbean.addOrderDetail(dbean);
		}
		osbean.setExpectTime(new Timestamp(info.getExpectTime().getTime()));
		osbean.setMemberBean(memberBean);
		osbean.setShopBean(sbean);
		osbean.setMemo(info.getMemo());
		osbean.setTotalPrice(smallprice);
		osbean.setOrderCondID(1);
		osbean.setOrderTime(new Timestamp(new Date().getTime()));
		boolean b =orderSumService.insertOrder(osbean);
		System.out.println(b);
		boolean b1 =memberFunctionService.useSpecialPrice(info.getMemberID());
		System.out.println(b1);
		return "newOrder";
	}
	
	public String selectAllShops(){
		List<ShopBean> list = shopService.selectAll();
		Iterator iterator = list.iterator();
		sbeanlist = new ArrayList<ShopBeanInfo>();
		ShopBeanInfo sbeani;
		
		while(iterator.hasNext()){
			ShopBean sbean = (ShopBean) iterator.next();
			if(sbean.getShopSuspend() ==false&&sbean.getShopCondID()==3){
				sbeani = new ShopBeanInfo();
				sbeani.setShopID(sbean.getShopID());
				sbeani.setShopAddr(sbean.getShopAddr());
				sbeani.setShopArea(sbean.getShopArea());
				sbeani.setShopCity(sbean.getShopCity());
				sbeani.setShopName(sbean.getShopName());
				sbeani.setShopPhone(sbean.getShopPhone());
				sbeani.setBeginBusinessTime(sbean.getBeginBusinessTime());
				sbeani.setEndBusinessTime(sbean.getEndBusinessTime());
				sbeanlist.add(sbeani);
			}
		}
		return "selectAllShops";
	}
	
	public String queryShopImage(){
		imageByte = shopService.select(shopID).getLogoImage();
		//編碼
//		final Base64.Encoder encoder = Base64.getEncoder();
//		encodedText = encoder.encodeToString(b);
//		encodedText = "data:image/jpg;base64, "+encodedText;
//		this.inputStream = new StringBufferInputStream(encodedText);
//		System.out.println(encodedText);
		return "ShopImage";
	}
}
