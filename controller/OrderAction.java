package controller;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.bean.MealBean;
import model.bean.MealKindListBean;
import model.bean.ShopBean;
import model.service.OrderSumService;

public class OrderAction {
	private OrderSumService orderSumService;
	private Integer shopID;
	private Integer mealKindID;
	private Integer page;
	private Integer mealID;
	//list回傳結果
	private List<MealBean> mealBeanlist;
	private List<Map<String, Object>> mealKindlist;
	//圖片以String傳回
	private String encodedText;
	private InputStream inputStream;
	private byte[] imageByte;

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
		List<MealBean> list1 = orderSumService.selectShopMeal(shopID, mealKindID, page);
		mealBeanlist =new ArrayList<MealBean>();
		Iterator iterator = list1.iterator();
		while(iterator.hasNext()){
			//將MealImage設為Null加快傳輸，建立新的Bean存入查詢到Bean的資料
			MealBean temp = (MealBean) iterator.next();
			MealBean newbean = new MealBean();
			newbean.setMealID(temp.getMealID());
			newbean.setMealKindID(temp.getMealKindID());
			newbean.setMealName(temp.getMealName());
			newbean.setMealStatus(temp.getMealStatus());
			newbean.setPrice(temp.getPrice());
			newbean.setShopID(temp.getShopID());
			mealBeanlist.add(newbean);
		}
		System.out.println(mealBeanlist);
		Long l1=System.currentTimeMillis();
		System.out.println((l1-l));
		return "specificMeal";
	}
	
	//選出圖片資料
	public String queryMealImage(){
		imageByte =orderSumService.queryMealImage(mealID);
		//編碼
//		final Base64.Encoder encoder = Base64.getEncoder();
//		encodedText = encoder.encodeToString(b);
//		encodedText = "data:image/jpg;base64, "+encodedText;
//		this.inputStream = new StringBufferInputStream(encodedText);
//		System.out.println(encodedText);
		return "Image";
	}

	
 
	
}
